

import org.junit.Test;

import com.clubobsidian.raven.config.ConfigurationFile;
import com.clubobsidian.raven.config.UnknownFileTypeException;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

public class ConfigTest {

	@Test 
	public void testYaml() 
	{
		try 
		{
			ConfigurationFile file = ConfigurationFile.load(new File("test.yml"));
			assertTrue("testYaml get foo.bar should return 'true'", file.get("foo.bar") != null);
			assertTrue("testYaml getSection foo should return 'true'", file.getSection("foo") != null);
			assertTrue("testYaml getString foo.bar should return 'true'", file.getString("foo.bar").equals("value"));
			assertTrue("testYaml getInt int should return 'true'", file.getInt("int") == 1);
			assertTrue("testYaml getFloat float should return 'true'", file.getFloat("float") != 0);
			assertTrue("testYaml getDouble double should return 'true'", file.getDouble("double") != 0);
			assertTrue("testYaml getBoolean should return 'true'", file.getBoolean("boolean"));
			//assertTrue("testYaml get object-list should return 'true'", file.getObjectList("object-list") != null && file.getObjectList("object-list").size() == 5);
			assertTrue("testYaml get string-list should return 'true'", file.getStringList("string-list") != null && file.getStringList("string-list").size() == 2);
			assertTrue("testYaml get int-list should return 'true'", file.getIntList("int-list") != null && file.getIntList("int-list").size() == 2);
			assertTrue("testYaml get float-list should return 'true'", file.getFloatList("float-list") != null && file.getFloatList("float-list").size() == 2);
			assertTrue("testYaml get double-list should return 'true'", file.getDoubleList("double-list") != null && file.getDoubleList("double-list").size() == 2);
			assertTrue("testYaml get double-list should return 'true'", file.getBooleanList("boolean-list") != null && file.getBooleanList("boolean-list").size() == 2);
		} 
		catch (IOException | UnknownFileTypeException e) 
		{
			throw new AssertionError("testYaml cannot find file test.yml");
		} 
	}
}