import org.junit.Test;

import com.clubobsidian.raven.config.Configuration;
import com.clubobsidian.raven.config.ConfigurationType;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

public class ConfigTest {

	@Test 
	public void testYamlFile() 
	{

			Configuration config = Configuration.load(new File("test.yml"));
			assertTrue("testYaml get foo.bar should return 'true'", config.get("foo.bar") != null);
			assertTrue("testYaml getSection foo should return 'true'", config.getSection("foo") != null);
			assertTrue("testYaml getString foo.bar should return 'true'", config.getString("foo.bar").equals("baz"));
			assertTrue("testYaml getInt int should return 'true'", config.getInt("int") == 1);
			assertTrue("testYaml getFloat float should return 'true'", config.getFloat("float") != 0);
			assertTrue("testYaml getDouble double should return 'true'", config.getDouble("double") != 0);
			assertTrue("testYaml getBoolean should return 'true'", config.getBoolean("boolean"));
			assertTrue("testYaml get string-list should return 'true'", config.getStringList("string-list") != null && config.getStringList("string-list").size() == 2);
			assertTrue("testYaml get int-list should return 'true'", config.getIntList("int-list") != null && config.getIntList("int-list").size() == 2);
			assertTrue("testYaml get float-list should return 'true'", config.getFloatList("float-list") != null && config.getFloatList("float-list").size() == 2);
			assertTrue("testYaml get double-list should return 'true'", config.getDoubleList("double-list") != null && config.getDoubleList("double-list").size() == 2);
			assertTrue("testYaml get double-list should return 'true'", config.getBooleanList("boolean-list") != null && config.getBooleanList("boolean-list").size() == 2);		
	}
	
	@Test
	public void testHoconFile()
	{
			Configuration config = Configuration.load(new File("test.conf"));
			assertTrue("testHocon get foo.bar should return 'true'", config.get("foo.bar") != null);
			assertTrue("testHocon getSection foo should return 'true'", config.getSection("foo") != null);
			assertTrue("testHocon getString foo.bar should return 'true'", config.getString("foo.bar").equals("baz"));
			assertTrue("testHocon getInt int should return 'true'", config.getInt("int") == 1);
			assertTrue("testHocon getFloat float should return 'true'", config.getFloat("float") != 0);
			assertTrue("testHocon getDouble double should return 'true'", config.getDouble("double") != 0);
			assertTrue("testHocon getBoolean should return 'true'", config.getBoolean("boolean"));
			assertTrue("testHocon get string-list should return 'true'", config.getStringList("string-list") != null && config.getStringList("string-list").size() == 2);
			assertTrue("testHocon get int-list should return 'true'", config.getIntList("int-list") != null && config.getIntList("int-list").size() == 2);
			assertTrue("testHocon get float-list should return 'true'", config.getFloatList("float-list") != null && config.getFloatList("float-list").size() == 2);
			assertTrue("testHocon get double-list should return 'true'", config.getDoubleList("double-list") != null && config.getDoubleList("double-list").size() == 2);
			assertTrue("testHocon get double-list should return 'true'", config.getBooleanList("boolean-list") != null && config.getBooleanList("boolean-list").size() == 2);
	}
	
	@Test
	public void testJsonFile()
	{
			Configuration config = Configuration.load(new File("test.json"));
			assertTrue("testJson get foo.bar should return 'true'", config.get("foo.bar") != null);
			assertTrue("testJson getSection foo should return 'true'", config.getSection("foo") != null);
			assertTrue("testJson getString foo.bar should return 'true'", config.getString("foo.bar").equals("baz"));
			assertTrue("testJson getInt int should return 'true'", config.getInt("int") == 1);
			assertTrue("testJson getFloat float should return 'true'", config.getFloat("float") != 0);
			assertTrue("testJson getDouble double should return 'true'", config.getDouble("double") != 0);
			assertTrue("testJson getBoolean should return 'true'", config.getBoolean("boolean"));
			assertTrue("testJson get string-list should return 'true'", config.getStringList("string-list") != null && config.getStringList("string-list").size() == 2);
			assertTrue("testJson get int-list should return 'true'", config.getIntList("int-list") != null && config.getIntList("int-list").size() == 2);
			assertTrue("testJson get float-list should return 'true'", config.getFloatList("float-list") != null && config.getFloatList("float-list").size() == 2);
			assertTrue("testJson get double-list should return 'true'", config.getDoubleList("double-list") != null && config.getDoubleList("double-list").size() == 2);
			assertTrue("testJson get double-list should return 'true'", config.getBooleanList("boolean-list") != null && config.getBooleanList("boolean-list").size() == 2);
	}
	
	@Test 
	public void testYamlStream() 
	{
		try 
		{
			Configuration config = Configuration.load(new File("test.yml").toURI().toURL().openStream(), ConfigurationType.YAML);
			assertTrue("testYaml get foo.bar should return 'true'", config.get("foo.bar") != null);
			assertTrue("testYaml getSection foo should return 'true'", config.getSection("foo") != null);
			assertTrue("testYaml getString foo.bar should return 'true'", config.getString("foo.bar").equals("baz"));
			assertTrue("testYaml getInt int should return 'true'", config.getInt("int") == 1);
			assertTrue("testYaml getFloat float should return 'true'", config.getFloat("float") != 0);
			assertTrue("testYaml getDouble double should return 'true'", config.getDouble("double") != 0);
			assertTrue("testYaml getBoolean should return 'true'", config.getBoolean("boolean"));
			assertTrue("testYaml get string-list should return 'true'", config.getStringList("string-list") != null && config.getStringList("string-list").size() == 2);
			assertTrue("testYaml get int-list should return 'true'", config.getIntList("int-list") != null && config.getIntList("int-list").size() == 2);
			assertTrue("testYaml get float-list should return 'true'", config.getFloatList("float-list") != null && config.getFloatList("float-list").size() == 2);
			assertTrue("testYaml get double-list should return 'true'", config.getDoubleList("double-list") != null && config.getDoubleList("double-list").size() == 2);
			assertTrue("testYaml get double-list should return 'true'", config.getBooleanList("boolean-list") != null && config.getBooleanList("boolean-list").size() == 2);
		} 
		catch (IOException e) 
		{
			throw new AssertionError("testYaml : " + e.getMessage());
		} 
	}
	
	@Test
	public void testHoconStream()
	{
		try 
		{
			Configuration config = Configuration.load(new File("test.conf").toURI().toURL().openStream(), ConfigurationType.HOCON);
			assertTrue("testHocon get foo.bar should return 'true'", config.get("foo.bar") != null);
			assertTrue("testHocon getSection foo should return 'true'", config.getSection("foo") != null);
			assertTrue("testHocon getString foo.bar should return 'true'", config.getString("foo.bar").equals("baz"));
			assertTrue("testHocon getInt int should return 'true'", config.getInt("int") == 1);
			assertTrue("testHocon getFloat float should return 'true'", config.getFloat("float") != 0);
			assertTrue("testHocon getDouble double should return 'true'", config.getDouble("double") != 0);
			assertTrue("testHocon getBoolean should return 'true'", config.getBoolean("boolean"));
			assertTrue("testHocon get string-list should return 'true'", config.getStringList("string-list") != null && config.getStringList("string-list").size() == 2);
			assertTrue("testHocon get int-list should return 'true'", config.getIntList("int-list") != null && config.getIntList("int-list").size() == 2);
			assertTrue("testHocon get float-list should return 'true'", config.getFloatList("float-list") != null && config.getFloatList("float-list").size() == 2);
			assertTrue("testHocon get double-list should return 'true'", config.getDoubleList("double-list") != null && config.getDoubleList("double-list").size() == 2);
			assertTrue("testHocon get double-list should return 'true'", config.getBooleanList("boolean-list") != null && config.getBooleanList("boolean-list").size() == 2);
		} 
		catch (IOException e) 
		{
			throw new AssertionError("testHocon : " + e.getMessage());
		} 
	}
	
	@Test
	public void testJsonStream()
	{
		try 
		{
			Configuration file = Configuration.load(new File("test.json").toURI().toURL().openStream(), ConfigurationType.JSON);
			assertTrue("testJson get foo.bar should return 'true'", file.get("foo.bar") != null);
			assertTrue("testJson getSection foo should return 'true'", file.getSection("foo") != null);
			assertTrue("testJson getString foo.bar should return 'true'", file.getString("foo.bar").equals("baz"));
			assertTrue("testJson getInt int should return 'true'", file.getInt("int") == 1);
			assertTrue("testJson getFloat float should return 'true'", file.getFloat("float") != 0);
			assertTrue("testJson getDouble double should return 'true'", file.getDouble("double") != 0);
			assertTrue("testJson getBoolean should return 'true'", file.getBoolean("boolean"));
			assertTrue("testJson get string-list should return 'true'", file.getStringList("string-list") != null && file.getStringList("string-list").size() == 2);
			assertTrue("testJson get int-list should return 'true'", file.getIntList("int-list") != null && file.getIntList("int-list").size() == 2);
			assertTrue("testJson get float-list should return 'true'", file.getFloatList("float-list") != null && file.getFloatList("float-list").size() == 2);
			assertTrue("testJson get double-list should return 'true'", file.getDoubleList("double-list") != null && file.getDoubleList("double-list").size() == 2);
			assertTrue("testJson get double-list should return 'true'", file.getBooleanList("boolean-list") != null && file.getBooleanList("boolean-list").size() == 2);
		} 
		catch (IOException e) 
		{
			throw new AssertionError("testJson : " + e.getMessage());
		} 
	}
}