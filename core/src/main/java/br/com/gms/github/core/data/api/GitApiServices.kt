package br.com.gms.github.core.data.api

import br.com.gms.github.core.data.models.response.GitRepositoryResponse
import br.com.gms.github.core.data.models.response.GitPageResponse
import br.com.gms.github.core.data.models.response.GitPullRequestResponse
import br.com.gms.github.core.data.models.response.GitUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitApiServices {

    @GET("search/users")
    suspend fun loadGitUsers(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("page") page: String,
        @Query("per_page") pageSize: String,
    ): Response<GitPageResponse<GitUserResponse>>

    @GET("users/{username}")
    suspend fun loadUserInfo(
        @Path("username") username: String,
    ): Response<GitUserResponse>

    @GET("users/{username}/repos")
    suspend fun loadUserRepositories(
        @Path("username") username: String,
        @Query("sort") sort: String,
        @Query("page") page: String,
        @Query("per_page") pageSize: String,
    ): Response<List<GitRepositoryResponse>>

    @GET("repos/{username}/{repositoryName}/pulls")
    suspend fun loadAllPullsOfRepository(
        @Path("username") username: String,
        @Path("repositoryName") repositoryName: String,
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("page") page: String,
        @Query("per_page") pageSize: String,
    ): Response<List<GitPullRequestResponse>>
}