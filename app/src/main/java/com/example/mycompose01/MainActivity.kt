package com.example.mycompose01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mycompose01.Destination.*
import androidx.navigation.compose.rememberNavController
import com.example.mycompose01.model.data.SuperHero
import com.example.mycompose01.model.data.superheroes
import com.example.mycompose01.ui.theme.MyCompose01Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCompose01Theme {
                window.statusBarColor = MaterialTheme.colors.primaryVariant.toArgb()
                Surface(color = MaterialTheme.colors.background) {
                    NavigationComponent()
                }

            }
        }
    }
}
@Composable
fun NavigationComponent(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "MainActivity"){
        composable("MainActivity"){
            ListSuperhero(superheroes,navController)
        }
        composable(SecondActivity.ruta){ navBackStackEntry ->
            val nombre = navBackStackEntry.arguments?.getString("name")
            SecondScreen(nombre!!,superheroes)
        }
    }
}

@Composable
fun SecondScreen(nombreSuperheroe: String, superheroes: List<SuperHero>) {
    var i = 0
    val publisher:String
    val realName:String
    val image2:Int
    val superpoderes:String

    while(i<=6){
        if (superheroes[i].superhero == nombreSuperheroe){
           publisher = superheroes[i].publisher
           realName = superheroes[i].realName
           image2 = superheroes[i].image2
           superpoderes = superheroes[i].superpoderes

            Column(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = image2),
                    contentDescription = null,
                    modifier = Modifier
                        .size(400.dp)
                        .padding(50.dp),
                    contentScale = ContentScale.Fit,
                )

                Text(
                    modifier =
                    Modifier.padding(
                        start = 16.dp,
                        top = 30.dp,
                        end = 16.dp
                    ),
                    text = "Nombre real: $realName",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    modifier =
                    Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp
                    ),
                    text = "Creador: $publisher",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Text(
                    modifier =
                    Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp
                    ),
                    text = "Super poderes o habilidades: ",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp),
                    text = superpoderes,
                    fontSize = 15.sp

                )
            }
           break
        }else {
            i++
        }

    }

}

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun DefaultPreview() {
    MyCompose01Theme {
        NavigationComponent()
    }
}

@Composable
fun ListSuperhero(superheroes: List <SuperHero>,navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFF01A7C2),
                title = {
                    Text(
                        text = "SUPERHEROES",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(360.dp),
                        fontFamily = FontFamily.Serif
                    )
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    border = BorderStroke(3.dp, Color.Transparent),
                    shape = CutCornerShape(20.dp)
                ),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 25.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                }
            }

            items(superheroes) { SuperHero ->
                SuperHeroCard(SuperHero.superhero,SuperHero.image,navController)
            }
        }
    }
}

@Composable
fun SuperHeroCard(superHero: String,  image: Int,navController: NavController) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = {
                navController.navigate(SecondActivity.createRoute(superHero))}),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = Color(0xFF01A7C2),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .size(130.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit,
            )
            Column(
                Modifier
                .padding(8.dp)

            ) {
                Text(
                    text = superHero,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    style = MaterialTheme.typography.h4,
                    color = Color.White,
                )
            }
        }
    }
}



