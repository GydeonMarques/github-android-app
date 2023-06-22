package br.com.gms.github.core.util.paging

open class PageParamsRequest(
    var query: String = "",
    var sortBy: String = "",
    var pageSize: Int = PageConfig.PAGE_SIZE,
    var initialPage: Long = PageConfig.INITIAL_PAGE,
    var prefectDistance: Int = PageConfig.PREFECT_DISTANCE,
)