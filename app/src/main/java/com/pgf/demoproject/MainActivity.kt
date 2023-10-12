package com.pgf.demoproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Repositories(repos = mockRepositories)
                }
            }
        }
    }
}

@Composable
fun Repositories(repos: List<Repository>) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        for (repo in repos) {
            RepositoryItem(repo)
        }
    }
}

@Composable
fun RepositoryItem(repo: Repository) {
    Row(modifier = Modifier.height(64.dp).fillMaxWidth()) {
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