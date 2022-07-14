package com.example.reposearchapp.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject


class GithubLanguageColorUtil(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    val colorJson = "{\n" +
            "    \"ABAP\": \"#E8274B\", \n" +
            "    \"ActionScript\": \"#882B0F\", \n" +
            "    \"Ada\": \"#02f88c\", \n" +
            "    \"Agda\": \"#315665\", \n" +
            "    \"AGS Script\": \"#B9D9FF\", \n" +
            "    \"Alloy\": \"#64C800\", \n" +
            "    \"AMPL\": \"#E6EFBB\", \n" +
            "    \"ANTLR\": \"#9DC3FF\", \n" +
            "    \"API Blueprint\": \"#2ACCA8\", \n" +
            "    \"APL\": \"#5A8164\", \n" +
            "    \"Arc\": \"#aa2afe\", \n" +
            "    \"Arduino\": \"#bd79d1\", \n" +
            "    \"ASP\": \"#6a40fd\", \n" +
            "    \"AspectJ\": \"#a957b0\", \n" +
            "    \"Assembly\": \"#6E4C13\", \n" +
            "    \"ATS\": \"#1ac620\", \n" +
            "    \"AutoHotkey\": \"#6594b9\", \n" +
            "    \"AutoIt\": \"#1C3552\", \n" +
            "    \"BlitzMax\": \"#cd6400\", \n" +
            "    \"Boo\": \"#d4bec1\", \n" +
            "    \"Brainfuck\": \"#2F2530\", \n" +
            "    \"C Sharp\": \"#178600\", \n" +
            "    \"C\": \"#555555\", \n" +
            "    \"Chapel\": \"#8dc63f\", \n" +
            "    \"Cirru\": \"#ccccff\", \n" +
            "    \"Clarion\": \"#db901e\", \n" +
            "    \"Clean\": \"#3F85AF\", \n" +
            "    \"Click\": \"#E4E6F3\", \n" +
            "    \"Clojure\": \"#db5855\", \n" +
            "    \"CoffeeScript\": \"#244776\", \n" +
            "    \"ColdFusion CFC\": \"#ed2cd6\", \n" +
            "    \"ColdFusion\": \"#ed2cd6\", \n" +
            "    \"Common Lisp\": \"#3fb68b\", \n" +
            "    \"Component Pascal\": \"#b0ce4e\", \n" +
            "    \"cpp\": \"#f34b7d\", \n" +
            "    \"Crystal\": \"#776791\", \n" +
            "    \"CSS\": \"#563d7c\", \n" +
            "    \"D\": \"#ba595e\", \n" +
            "    \"Dart\": \"#00B4AB\", \n" +
            "    \"Diff\": \"#88dddd\", \n" +
            "    \"DM\": \"#447265\", \n" +
            "    \"Dogescript\": \"#cca760\", \n" +
            "    \"Dylan\": \"#6c616e\", \n" +
            "    \"E\": \"#ccce35\", \n" +
            "    \"Eagle\": \"#814C05\", \n" +
            "    \"eC\": \"#913960\", \n" +
            "    \"ECL\": \"#8a1267\", \n" +
            "    \"edn\": \"#db5855\", \n" +
            "    \"Eiffel\": \"#946d57\", \n" +
            "    \"Elixir\": \"#6e4a7e\", \n" +
            "    \"Elm\": \"#60B5CC\", \n" +
            "    \"Emacs Lisp\": \"#c065db\", \n" +
            "    \"EmberScript\": \"#FFF4F3\", \n" +
            "    \"Erlang\": \"#B83998\", \n" +
            "    \"F#\": \"#b845fc\", \n" +
            "    \"Factor\": \"#636746\", \n" +
            "    \"Fancy\": \"#7b9db4\", \n" +
            "    \"Fantom\": \"#dbded5\", \n" +
            "    \"FLUX\": \"#88ccff\", \n" +
            "    \"Forth\": \"#341708\", \n" +
            "    \"FORTRAN\": \"#4d41b1\", \n" +
            "    \"FreeMarker\": \"#0050b2\", \n" +
            "    \"Frege\": \"#00cafe\", \n" +
            "    \"Game Maker Language\": \"#8fb200\", \n" +
            "    \"Glyph\": \"#e4cc98\", \n" +
            "    \"Gnuplot\": \"#f0a9f0\", \n" +
            "    \"Go\": \"#375eab\", \n" +
            "    \"Golo\": \"#88562A\", \n" +
            "    \"Gosu\": \"#82937f\", \n" +
            "    \"Grammatical Framework\": \"#79aa7a\", \n" +
            "    \"Groovy\": \"#e69f56\", \n" +
            "    \"Handlebars\": \"#01a9d6\", \n" +
            "    \"Harbour\": \"#0e60e3\", \n" +
            "    \"Haskell\": \"#29b544\", \n" +
            "    \"Haxe\": \"#df7900\", \n" +
            "    \"HTML\": \"#e44b23\", \n" +
            "    \"Hy\": \"#7790B2\", \n" +
            "    \"IDL\": \"#a3522f\", \n" +
            "    \"Io\": \"#a9188d\", \n" +
            "    \"Ioke\": \"#078193\", \n" +
            "    \"Isabelle\": \"#FEFE00\", \n" +
            "    \"J\": \"#9EEDFF\", \n" +
            "    \"Java\": \"#b07219\", \n" +
            "    \"JavaScript\": \"#f1e05a\", \n" +
            "    \"JFlex\": \"#DBCA00\", \n" +
            "    \"JSONiq\": \"#40d47e\", \n" +
            "    \"Julia\": \"#a270ba\", \n" +
            "    \"Jupyter Notebook\": \"#DA5B0B\", \n" +
            "    \"Kotlin\": \"#F18E33\", \n" +
            "    \"KRL\": \"#28431f\", \n" +
            "    \"Lasso\": \"#999999\", \n" +
            "    \"Latte\": \"#A8FF97\", \n" +
            "    \"Lex\": \"#DBCA00\", \n" +
            "    \"LFE\": \"#004200\", \n" +
            "    \"LiveScript\": \"#499886\", \n" +
            "    \"LOLCODE\": \"#cc9900\", \n" +
            "    \"LookML\": \"#652B81\", \n" +
            "    \"LSL\": \"#3d9970\", \n" +
            "    \"Lua\": \"#000080\", \n" +
            "    \"Makefile\": \"#427819\", \n" +
            "    \"Mask\": \"#f97732\", \n" +
            "    \"Matlab\": \"#bb92ac\", \n" +
            "    \"Max\": \"#c4a79c\", \n" +
            "    \"MAXScript\": \"#00a6a6\", \n" +
            "    \"Mercury\": \"#ff2b2b\", \n" +
            "    \"Metal\": \"#8f14e9\", \n" +
            "    \"Mirah\": \"#c7a938\", \n" +
            "    \"MTML\": \"#b7e1f4\", \n" +
            "    \"NCL\": \"#28431f\", \n" +
            "    \"Nemerle\": \"#3d3c6e\", \n" +
            "    \"nesC\": \"#94B0C7\",\n" +
            "    \"NetLinx\": \"#0aa0ff\", \n" +
            "    \"NetLinx+ERB\": \"#747faa\", \n" +
            "    \"NetLogo\": \"#ff6375\", \n" +
            "    \"NewLisp\": \"#87AED7\", \n" +
            "    \"Nimrod\": \"#37775b\", \n" +
            "    \"Nit\": \"#009917\", \n" +
            "    \"Nix\": \"#7e7eff\", \n" +
            "    \"Nu\": \"#c9df40\", \n" +
            "    \"Objective-C\": \"#438eff\", \n" +
            "    \"Objective-C++\": \"#6866fb\", \n" +
            "    \"Objective-J\": \"#ff0c5a\", \n" +
            "    \"OCaml\": \"#3be133\", \n" +
            "    \"Omgrofl\": \"#cabbff\", \n" +
            "    \"ooc\": \"#b0b77e\", \n" +
            "    \"Opal\": \"#f7ede0\", \n" +
            "    \"Oxygene\": \"#cdd0e3\", \n" +
            "    \"Oz\": \"#fab738\", \n" +
            "    \"Pan\": \"#cc0000\", \n" +
            "    \"Papyrus\": \"#6600cc\", \n" +
            "    \"Parrot\": \"#f3ca0a\", \n" +
            "    \"Pascal\": \"#b0ce4e\", \n" +
            "    \"PAWN\": \"#dbb284\", \n" +
            "    \"Perl\": \"#0298c3\", \n" +
            "    \"Perl6\": \"#0000fb\", \n" +
            "    \"PHP\": \"#4F5D95\", \n" +
            "    \"PigLatin\": \"#fcd7de\", \n" +
            "    \"Pike\": \"#005390\", \n" +
            "    \"PLSQL\": \"#dad8d8\", \n" +
            "    \"PogoScript\": \"#d80074\", \n" +
            "    \"Processing\": \"#0096D8\", \n" +
            "    \"Prolog\": \"#74283c\", \n" +
            "    \"Propeller Spin\": \"#7fa2a7\", \n" +
            "    \"Puppet\": \"#302B6D\", \n" +
            "    \"Pure Data\": \"#91de79\", \n" +
            "    \"PureBasic\": \"#5a6986\", \n" +
            "    \"PureScript\": \"#1D222D\", \n" +
            "    \"Python\": \"#3572A5\", \n" +
            "    \"QML\": \"#44a51c\", \n" +
            "    \"R\": \"#198ce7\", \n" +
            "    \"Racket\": \"#22228f\", \n" +
            "    \"Ragel in Ruby Host\": \"#9d5200\", \n" +
            "    \"RAML\": \"#77d9fb\", \n" +
            "    \"Rebol\": \"#358a5b\", \n" +
            "    \"Red\": \"#ee0000\", \n" +
            "    \"Ren'Py\": \"#ff7f7f\", \n" +
            "    \"Rouge\": \"#cc0088\", \n" +
            "    \"Ruby\": \"#701516\", \n" +
            "    \"Rust\": \"#dea584\", \n" +
            "    \"SaltStack\": \"#646464\", \n" +
            "    \"SAS\": \"#B34936\", \n" +
            "    \"Scala\": \"#DC322F\", \n" +
            "    \"Scheme\": \"#1e4aec\", \n" +
            "    \"Self\": \"#0579aa\", \n" +
            "    \"Shell\": \"#89e051\", \n" +
            "    \"Shen\": \"#120F14\", \n" +
            "    \"Slash\": \"#007eff\", \n" +
            "    \"Slim\": \"#ff8f77\", \n" +
            "    \"Smalltalk\": \"#596706\", \n" +
            "    \"SourcePawn\": \"#5c7611\", \n" +
            "    \"SQF\": \"#3F3F3F\", \n" +
            "    \"Squirrel\": \"#800000\", \n" +
            "    \"Stan\": \"#b2011d\", \n" +
            "    \"Standard ML\": \"#dc566d\", \n" +
            "    \"SuperCollider\": \"#46390b\", \n" +
            "    \"Swift\": \"#ffac45\", \n" +
            "    \"SystemVerilog\": \"#DAE1C2\", \n" +
            "    \"Tcl\": \"#e4cc98\", \n" +
            "    \"TeX\": \"#3D6117\", \n" +
            "    \"Turing\": \"#45f715\", \n" +
            "    \"TypeScript\": \"#2b7489\", \n" +
            "    \"Unified Parallel C\": \"#4e3617\", \n" +
            "    \"Unity3D Asset\": \"#ab69a1\", \n" +
            "    \"UnrealScript\": \"#a54c4d\", \n" +
            "    \"Vala\": \"#fbe5cd\", \n" +
            "    \"Verilog\": \"#b2b7f8\", \n" +
            "    \"VHDL\": \"#adb2cb\", \n" +
            "    \"VimL\": \"#199f4b\", \n" +
            "    \"Visual Basic\": \"#945db7\", \n" +
            "    \"Volt\": \"#1F1F1F\", \n" +
            "    \"Vue\": \"#2c3e50\", \n" +
            "    \"Web Ontology Language\": \"#9cc9dd\", \n" +
            "    \"wisp\": \"#7582D1\", \n" +
            "    \"X10\": \"#4B6BEF\", \n" +
            "    \"xBase\": \"#403a40\", \n" +
            "    \"XC\": \"#99DA07\", \n" +
            "    \"XQuery\": \"#5232e7\", \n" +
            "    \"Zephir\": \"#118f9e\" \n" +
            "}"

    var colorMap: Map<String, String>? = null

    suspend fun parseJson() {
        withContext(defaultDispatcher) {
            colorMap = Json.parseToJsonElement(colorJson).jsonObject.toMap()
                .mapValues {
                    it.value.toString().replace(
                        "\"",
                        "") }
        }
    }
}