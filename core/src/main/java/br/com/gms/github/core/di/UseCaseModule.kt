package br.com.gms.github.core.di

import br.com.gms.github.core.domain.usecase.user.GitUsersUseCase
import br.com.gms.github.core.domain.usecase.user.GitUsersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGitUsersUseCase(useCase: GitUsersUseCaseImpl): GitUsersUseCase

}