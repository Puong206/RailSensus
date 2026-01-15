package com.example.railsensus.uicontroller.route

object DestinasiLokoDetail : DestinasiNavigasi {
    override val route = "detail_loko"
    override val titleRes = "Detail Lokomotif"
    
    const val lokoIdArg = "lokoId"
    val routeWithArgs = "$route/{$lokoIdArg}"
    
    fun createRoute(lokoId: Int) = "$route/$lokoId"
}
