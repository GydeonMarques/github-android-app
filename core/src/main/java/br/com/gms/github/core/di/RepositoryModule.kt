package br.com.gms.github.core.di

import br.com.gms.github.core.data.repository.user.GitUsersRepository
import br.com.gms.github.core.data.repository.user.GitUsersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindGitUsersRepository(repository: GitUsersRepositoryImpl): GitUsersRepository

}