package sebastiancorradi.magazine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
@Preview
fun example(){
    var task by remember {  mutableStateOf( "")}
    var taskList = remember {  mutableStateOf(emptyList<Item>())}
    Column(modifier = Modifier.fillMaxSize()
        .background(color = Color.Blue),
            verticalArrangement = Arrangement.Top)
    {
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(value = task, onValueChange = {
                task = it
            })
            Button(onClick = {
                taskList.value += Item(false, task)
                task = ""
            }) {
                Text("Add")
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            items(taskList.value.size) { item ->
                Row() {
                    Checkbox(checked = taskList.value[item].checked, onCheckedChange = {
                        val newList = mutableListOf<Item>()
                        newList.addAll(taskList.value)
                        newList[item] = Item(!newList[item].checked,newList[item].text)
                        taskList.value = newList
                    })
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = taskList.value[item].text,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                }
                HorizontalDivider()
            }
        }
    }
}

data class Item(
    var checked: Boolean,
    val text: String
){

}