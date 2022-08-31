import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import java.io.InputStream;

public class TeamTester {

	public static Team team;

	@BeforeClass
	public void setup() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("team.json");
		team = new ObjectMapper().readValue(inputStream, Team.class);
	}

	@Test
	public void numberOfForeignPlayerTest() throws IOException {
		int count = 0;
		for(Player player : team.getPlayer()){
			if(!player.getCountry().equalsIgnoreCase("India")){
				count = count + 1;
			}
		}

		Assert.assertEquals(count, 4, "Team should have only 4 foreign players");
	}

	@Test
	public void numberOfKeeperTest() throws IOException {
		int count = 0;
		for(Player player : team.getPlayer()){
			if(player.getRole().equalsIgnoreCase("Wicket-keeper")){
				count = count + 1;
			}
		}

		Assert.assertEquals(count, 1, "Team should have atleast one keeper");
	}
}
