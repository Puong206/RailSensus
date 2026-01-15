package com.example.railsensus.uicontroller.route

object DestinasiKeretaDetail : DestinasiNavigasi {
    override val route = "detail_kereta"
    override val titleRes = "Detail Kereta"
    
    const val keretaIdArg = "keretaId"
    val routeWithArgs = "$route/{$keretaIdArg}"
    
    fun createRoute(keretaId: Int) = "$route/$keretaId"
}
