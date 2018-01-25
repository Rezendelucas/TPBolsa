package com.mygdx.game.UtilsPack;

/**
 * Created by LucasRezende on 03/08/2017.
 */
public class Constants {
    // Valores
    public  static  final float VIEWPORT_WIDTH  = 15.0f;// largura visivel game word 5 metros
    public  static  final float VIEWPORT_HEIGHT = 15.0f;// altura visivel game word 5 metros
    public  static  final float VIEWPORT_GUI_WIDTH = 800.f;//800
    public  static  final float VIEWPORT_GUI_HEIGHT = 480.0f;//480
    // Leveis
    public  static  final String LEVEL_01 = "";
    // Caminho de recursos graficos
    public static final String TEXTURE_ATLAS_OBJECTS =  "assets_atlas/Resources_RogueCode.pack";
    public static final String TEXTURE_ATLAS_UI ="assets_atlas_ui/GameUI.pack";
    public static final String SKIN_GAME_UI ="assets_atlas_ui/menu.json";

    public static final String UISKIN = "assets_utils/skins/uiskin.json";
    public static final String TEXTURE_ATLAS_LIBGDX_UI ="assets_utils/skins/uiskin.atlas";
    public static final String SKIN_LIBGDX_UI ="";
    // Fontes
    public static final String FONT_SOURCE = "assets_atlas/arial-15.fnt";
    //Estados
    public static final int SEM_LAÇO = 0;//nao existe um laço ativo
    public static final int LAÇO_ABERTO = 1;//existe um laço aberto e nao fechado
    public static final int LAÇO_FECHADO = 2;//existe um laço com clausula de parada mas sem flag final
    public static final int LAÇO_CONCLUIDO = 3;// existe um laço ativo e bem definido
}


