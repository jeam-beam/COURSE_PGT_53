package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class GeoIpServieTests {

  @Test
  public void testMyIp() {
   // String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("88.206.25.243");
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("88.206.25.243");
    assertTrue(ipLocation.contains("<GeoIP><Country>RU</Country><State>13</State></GeoIP>"));
  }

  @Test
  public void testInvalidIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("88.206.25.xxx");
    assertTrue(ipLocation == null);
  }
}
