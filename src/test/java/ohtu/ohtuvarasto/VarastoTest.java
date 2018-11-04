package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriNegatiivinen() {
        Varasto toinen = new Varasto(-1);
        
        //varaston koko on 0
        
        assertEquals(0, toinen.getSaldo(), vertailuTarkkuus);
        assertEquals(0, toinen.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriAlkusaldolla() {
        Varasto varasto2 = new Varasto(10, 2);
        
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
        assertEquals(2, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriAlkusaldollaNegatiivinenTilavuus() {
        Varasto varasto2 = new Varasto(-10, 2);
        
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);

    }
    
    @Test
    public void konstruktoriAlkusaldollaNegatiivinenSaldo() {
        Varasto varasto2 = new Varasto(10, -2);
        
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriAlkusaldollaSuuriSaldo() {
        Varasto varasto2 = new Varasto(10, 12);
        
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaSaldoaNegatiivinen() {
        varasto.lisaaVarastoon(8);
        varasto.lisaaVarastoon(-6);

        // vain ensimmäisen lisäyksen pitäisi voimia
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaysEnemmanKuinMahtuu() {
        varasto.lisaaVarastoon(12);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenNegatiivinenMaara(){
        varasto.lisaaVarastoon(8);
        
        double saatuMaara = varasto.otaVarastosta(-2);
        
        //negatiivinen ottaminen palauttaa 0 ja pitää saldon ennallaan
        
        assertEquals(0, saatuMaara, vertailuTarkkuus);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenEnemmanKuinSaldo(){
        varasto.lisaaVarastoon(8);
        
        double saatuMaara = varasto.otaVarastosta(9);
        
        //varasto antaa minkä voi
        
        assertEquals(8, saatuMaara, vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void testaaToStrig(){
        varasto.lisaaVarastoon(8);
        
        String s = varasto.toString();
        
        assertEquals("saldo = 8.0, vielä tilaa 2.0", s);
    }

}
