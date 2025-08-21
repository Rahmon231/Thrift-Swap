package com.dev.thriftswap.di

import com.dev.thriftswap.domain.repository.AuthenticationRepository
import com.dev.thriftswap.data.repositories.AuthenticationRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideThriftsCollection(db: FirebaseFirestore): CollectionReference {
        return db.collection("thrifts")
    }

    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): AuthenticationRepository = AuthenticationRepositoryImpl(auth, firestore)

}