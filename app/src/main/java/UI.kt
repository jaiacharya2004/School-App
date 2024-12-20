import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.schoolapp.R

@Composable
fun ID() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),


    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(16.dp) // Adjust padding as needed
                    .size(200.dp)
                    .clickable {
                    }
            ) {
                Text(
                    text = "         Principal Login",
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                )
                Image(
                    painter = painterResource(id = R.drawable.principal),
                    contentDescription = "Vertical Card Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // Space between cards

            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(16.dp) // Adjust padding as needed
                    .size(200.dp)
                    .clickable { }
            ) {
                Text(
                    text = "         Teacher Login",
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                )
                Image(
                    painter = painterResource(id = R.drawable.teacher),
                    contentDescription = "Vertical Card Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // Space between cards

            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(16.dp) // Adjust padding as needed
                    .size(200.dp)
                    .clickable { }
            ) {
                Text(
                    text = "         Student Login",
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                )
                Image(
                    painter = painterResource(id = R.drawable.student),
                    contentDescription = "Vertical Card Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
