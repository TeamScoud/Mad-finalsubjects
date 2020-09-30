package application.example.mad2;

import android.content.Context;
import android.database.Cursor;
import android.view.View;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private MainActivity mainActivity;

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Before
    public void setUp() {
        mainActivity = new MainActivity();
    }

}