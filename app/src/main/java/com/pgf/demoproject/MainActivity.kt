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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pgf.demoproject.ui.theme.DemoProjectTheme

class MainActivity : ComponentActivity() {

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
                        RepositoriesContainer()
                    }
                }
            }
        }
    }
}

@Composable
fun RepositoriesContainer(mainViewModel: MainViewModel = viewModel()) {
    val context = LocalContext.current

    val uiState by mainViewModel.uiState.collectAsState()

    Repositories(repositories = uiState.repositories) {
        val intent = Intent(context, DetailActivity::class.java).apply {
            putExtra("repo", it)
        }
        context.startActivity(intent)
    }
}

@Composable
fun Repositories(repositories: List<Repository> = emptyList(), onClick: (Repository) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(repositories.size) { index ->
            val repo = repositories[index]
            RepositoryItem(repo = repo) {
                onClick(repo)
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

    val context = LocalContext.current

    val mockRepositories = List(20) { i ->
        Repository(
            name = "Repository $i",
            description = "Description $i",
            stars = i * 10,
            forks = i * 5
        )
    }

    DemoProjectTheme {
        Repositories(repositories = mockRepositories) {
            Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}