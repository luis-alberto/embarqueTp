package fr.imie.imiendk;

import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Main class project
 * @author Luis
 *
 */
public class MainActivity extends Activity {
    /**
     * Button component C.
     */
    private Button btC;
    /**
     * Button component JAVA.
     */
    private Button btJava;
    /**
     * EditText component Number.
     */
    private EditText etNumber;
    /**
     * TextView component list of numbers.
     */
    private TextView tvListNumbers;
    /**
     * Add C library.
     */
    static {
        System.loadLibrary("prime-jni");
    }
    /**
     * Check if a number is prime.
     * @param nb int check number.
     * @return String is or not a prime number.
     */
    public native String checkPrime(int nb);
    /**
     * Get all the primes into 0 and nb.
     * @param nb check number.
     * @return array of numbers primes.
     */
    public native int[] getAllPrimes(int nb);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btC = (Button) this.findViewById(R.id.buttonC);
        btC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nb;
                String test;
                String text;
                long time;
                int[] tabInt;

                etNumber = (EditText) findViewById(R.id.number);
                tvListNumbers = (TextView) findViewById(R.id.listNumbers);

                text = etNumber.getText().toString();
                nb = Integer.parseInt(text);

                test = checkPrime(nb);
                Toast.makeText(getApplicationContext(),test
                        , Toast.LENGTH_SHORT).show();

                time = System.currentTimeMillis();
                tabInt = getAllPrimes(nb);
                tvListNumbers.setText(Arrays.toString(tabInt).replace("[", "").replace("]", ""));
                time=System.currentTimeMillis()-time;
                Toast.makeText(getApplicationContext(),"Time: "+time+" ms"
                        , Toast.LENGTH_SHORT).show();
            }
        });

        btJava = (Button) this.findViewById(R.id.buttonJava);
        btJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long time;
                String test;
                String text;
                int nb;
                etNumber = (EditText) findViewById(R.id.number);
                tvListNumbers = (TextView) findViewById(R.id.listNumbers);

                text = etNumber.getText().toString();
                nb = Integer.parseInt(text);

                test=checkPrimeJava(nb);
                Toast.makeText(getApplicationContext(),test
                        , Toast.LENGTH_SHORT).show();

                time = System.currentTimeMillis();
                tvListNumbers.setText(getAllPrimesJava(nb));
                time=System.currentTimeMillis()-time;
                Toast.makeText(getApplicationContext(),"Time: "+time+" ms"
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * Check if a number is a prime.
     * @param nb check number.
     * @return String is or not are prime number.
     */
    private String checkPrimeJava(int nb){
        int i;
        if(nb == 1 || nb == 0)
            return "N'est un nombre premier";
        i=2;
        while(i<nb){
            if(nb%i==0){
                return "N'est un nombre premier";
            }
            i++;
        }
        return "Est un nombre premier";
    }

    /**
     * Get all primes numbers between 0 and nb.
     * @param nb check number.
     * @return List of all primes numbers between 0 and nb.
     */
    private String getAllPrimesJava(int nb){
        int i,j;
        int test;
        String result="";

        i=2;
        while(i<=nb){
            test=0;
            for(j=2;j<=i-1;j++){
                if(i%j==0){
                    test=1;
                    break;
                }
            }
            if(test==0){
                if(result!=""){
                    result+=", ";
                }
                result+=String.valueOf(j);
            }
            i++;
        }
        return result;
    }
}
