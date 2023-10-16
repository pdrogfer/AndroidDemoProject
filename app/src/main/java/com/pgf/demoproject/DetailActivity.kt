package com.pgf.demoproject

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pgf.demoproject.ui.theme.DemoProjectTheme

class DetailActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val repo = intent.extras?.getParcelable<Repository>("repo")

        setContent {
            DemoProjectTheme {
                if (repo != null) {
                    RepositoryDetail(repo = repo)
                } else {
                    EmptyDetail()
                }
            }
        }
    }
}

@Composable
private fun RepositoryDetail(repo: Repository) {
    Column {
        Text(text = repo.name)
        Text(text = repo.description)
        Text(text = "Stars: ${repo.stars}")
        Text(text = "Forks: ${repo.forks}")
    }
}

private fun EmptyDetail() {
    // TODO()
}


@Preview(showBackground = true)
@Composable
fun RepositoryDetailPreview() {
    DemoProjectTheme {
        RepositoryDetail(repo = Repository("Name", "Description", 10, 5))
    }
}