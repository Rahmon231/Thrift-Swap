package com.dev.thriftswap.data.repositories


import com.dev.thriftswap.data.model.ThriftUser
import com.dev.thriftswap.domain.repository.AuthenticationRepository
import com.dev.thriftswap.utils.Response
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthenticationRepository {

    override suspend fun userUid(): String = auth.currentUser?.uid ?: ""

    override suspend fun isLoggedIn(): Boolean = auth.currentUser != null

    override suspend fun logout() = auth.signOut()

    override suspend fun login(email: String, password: String): Flow<Response<AuthResult>> = flow {
        try {
            emit(Response.Loading)
            val data = auth.signInWithEmailAndPassword(email, password).await()
            emit(Response.Success(data))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Oops, something went wrong."))
        }
    }

    override suspend fun register(email: String, password: String): Flow<Response<AuthResult>> =
        flow {
            try {
                emit(Response.Loading)
                val data = auth.createUserWithEmailAndPassword(email, password).await()
                val uid = data.user?.uid ?: throw Exception("User UID is null")
                val userMap = ThriftUser(
                    userId = uid.toString(),
                    displayName = email.substringBefore("@"),
                    photoUrl = "",
                    id = null
                ).toMap()

                // Save user to Firestore
                firestore.collection("users").document(uid).set(userMap).await()
                emit(Response.Success(data))
            } catch (e: Exception) {
                emit(Response.Error(e.localizedMessage ?: "Oops, something went wrong."))
            }
        }

    override suspend fun resetPassword(email: String): Flow<Response<Void?>> = flow {
        try {
            emit(Response.Loading)
            val data = auth.sendPasswordResetEmail(email).await()
            emit(Response.Success(data))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Oops, something went wrong."))
        }
    }

    override suspend fun observeUsername(uid: String): Flow<String?> = callbackFlow {
        val listener = firestore.collection("users")
            .document(uid)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(null)
                    return@addSnapshotListener
                }
                trySend(snapshot?.getString("display_name"))
            }
        awaitClose { listener.remove() }
    }

    override suspend fun loginWithGoogle(idToken: String): Flow<Response<AuthResult>> = flow {
        try {
            emit(Response.Loading)
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = auth.signInWithCredential(credential).await()

            // Optionally, save the user to Firestore if new
            val user = result.user
            if (user != null) {
                val userDoc = firestore.collection("users").document(user.uid).get().await()
                if (!userDoc.exists()) {
                    val userMap = ThriftUser(
                        userId = user.uid,
                        displayName = user.displayName ?: "Unknown",
                        photoUrl = user.photoUrl?.toString() ?: "",
                        id = null
                    ).toMap()
                    firestore.collection("users").document(user.uid).set(userMap).await()
                }
            }

            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Oops, something went wrong."))
        }
    }



}