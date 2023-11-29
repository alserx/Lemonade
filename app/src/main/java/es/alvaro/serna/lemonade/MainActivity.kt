package es.alvaro.serna.lemonade

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.alvaro.serna.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Lemonade",
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = colorResource(R.color.lemon),
                titleContentColor = colorResource(R.color.black),
                navigationIconContentColor = colorResource(R.color.lemon),
                actionIconContentColor = colorResource(R.color.lemon)
            )
        )
    }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = colorResource(R.color.light_lemon)
        ) {
            LemonAppContent()
        }
    }

}

@Composable
fun LemonAppContent(modifier: Modifier = Modifier) {
    var activeButton by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (activeButton) {
            1 -> LemonButton(
                itemClicked = {
                    squeezeCount = (2..4).random()
                    activeButton = 2
                },
                textResourceId = R.string.tree_string,
                imageResourceId = R.drawable.lemon_tree,
                descriptionId = R.string.lemon_tree
            )

            2 -> LemonButton(
                itemClicked = {
                    squeezeCount--

                    if (squeezeCount <= 0)
                        activeButton = 3
                },
                textResourceId = R.string.lemon_string,
                imageResourceId = R.drawable.lemon_squeeze,
                descriptionId = R.string.lemon
            )

            3 -> LemonButton(
                itemClicked = {
                    activeButton = 4
                },
                textResourceId = R.string.lemonade_string,
                imageResourceId = R.drawable.lemon_drink,
                descriptionId = R.string.lemonade_glass
            )

            4 -> LemonButton(
                itemClicked = {
                    activeButton = 1
                },
                textResourceId = R.string.glass_string,
                imageResourceId = R.drawable.lemon_restart,
                descriptionId = R.string.empty_glass
            )
        }
    }
}


@Composable
fun LemonButton(
    modifier: Modifier = Modifier, itemClicked: () -> Unit,
    textResourceId: Int, imageResourceId: Int, descriptionId: Int
) {

    Button(
        onClick = itemClicked,
        shape = RoundedCornerShape(10),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.light_teal))
    ) {
        Image(
            painter = painterResource(imageResourceId),
            contentDescription = stringResource(descriptionId),
            modifier = modifier
                .padding(10.dp)
        )
    }

    Spacer(modifier.size(16.dp))
    Text(
        text = stringResource(textResourceId),
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
        color = colorResource(R.color.black)
    )
}
