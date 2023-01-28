package xyz.teamgravity.animatedcountertext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import xyz.teamgravity.animatedcountertext.ui.theme.AnimatedCounterTextTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatedCounterTextTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        var count by remember { mutableStateOf(0) }

                        AnimatedCounterText(count = count)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { count++ }
                        ) {
                            Text(text = stringResource(id = R.string.increment))
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedCounterText(
    count: Int,
) {
    Row {
        count.toString().forEach { character ->
            AnimatedContent(
                targetState = character,
                transitionSpec = { slideInVertically { it } with slideOutVertically { -it } },
            ) { char ->
                Text(
                    text = char.toString(),
                    style = MaterialTheme.typography.headlineLarge,
                    softWrap = false
                )
            }
        }
    }
}