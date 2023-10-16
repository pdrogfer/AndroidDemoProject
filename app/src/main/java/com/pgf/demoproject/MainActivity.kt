package com.pgf.demoproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.pgf.demoproject.ui.theme.DemoProjectTheme

class MainActivity : ComponentActivity() {

    private val mockRepositories = List(20) { i ->
        Repository(
            name = "Repository $i",
            description = "Description $i",
            stars = i * 10,
            forks = i * 5
        )
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Scaffold(topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),

                            title = {
                            Text(text = "Repositories")
                        })
                    }) {
                        Repositories(repos = mockRepositories)
                    }
                }
            }
        }
    }
}

@Composable
fun Repositories(repos: List<Repository>) {
    val context = LocalContext.current

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(repos.size) { index ->
            val repo = repos[index]
            RepositoryItem(repo = repo) {
                Toast.makeText(context, "Clicked on ${repo.name}", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra("repo", repo)
                }
                context.startActivity(intent)
            }
        }
    }
}

@Composable
fun RepositoryItem(repo: Repository, onClick: () -> Unit) {
    Row(modifier = Modifier
        .height(64.dp)
        .padding(horizontal = 16.dp)
        .fillMaxWidth()
        .clickable {
            onClick()
        }) {
        Text(text = repo.name)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    val mockRepositories = List(20) { i ->
        Repository(
            name = "Repository $i",
            description = "Description $i",
            stars = i * 10,
            forks = i * 5
        )
    }

    DemoProjectTheme {
        Repositories(repos = mockRepositories)
    }
}