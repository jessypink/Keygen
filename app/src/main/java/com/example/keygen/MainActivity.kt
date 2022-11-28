package com.example.keygen

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.keygen.databinding.ActivityMainBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.color.DynamicColors
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    val APP_PREFERENCES = "mysettings"
    val APP_PREFERENCES_NAME = "NameOfPass" // имя кота
    val APP_PREFERENCES_AGE = "Pass" // возраст кота
    val APP_PREFERENCES_COUNT = "0" // возраст кота



    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyIfAvailable(this)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main)
        binding.bottomNavigation.setupWithNavController(navController)

        setSupportActionBar(binding.toolbar)
        var mSettings: SharedPreferences? = null
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        var APP_PREFERENCES = "mysettings"
        var APP_PREFERENCES_NAME = "NameOfPass" // имя кота
        var APP_PREFERENCES_AGE = "Pass" // возраст кота

        val editor: SharedPreferences.Editor = mSettings.edit()


        val autoCompleteTextView: AutoCompleteTextView = findViewById(R.id.autoCompleteTextView)

        val cats = resources.getStringArray((R.array.cats))
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, cats)

        val language = arrayOf<String>("C","C++","Java",".Net","Kotlin","Ruby","Rails","Python","Java Script","Php","Ajax","Perl","Hadoop")
        //val listView: ListView = findViewById(R.id.listView)
        //val arrayAdapter2 = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,language)
        //listView.adapter = arrayAdapter


        var listPass = mutableListOf(1)
        var listEnAlphabetSmall = mutableListOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
        var listCharacters = mutableListOf('!', '#', '*', '+')
        var countOfSelectedChips1 = 0


        //val tv2: TextView = findViewById(R.id.textView2)
        val passitog: TextInputEditText = findViewById(R.id.resultpass)

        val NameOfPass: TextInputEditText = findViewById(R.id.nammmeditt)

        val tv1: TextView = findViewById(R.id.textView7)
        val tv2: TextView = findViewById(R.id.textView5)
       // val tv2: TextInputEditText = findViewById(R.id.textView3)


        var countOfSavedPasswords = 0
        var y = 0

        val chpgr1: ChipGroup = findViewById(R.id.chipGroupCharacters)

        tv1.setText(mSettings.getString(APP_PREFERENCES_NAME, ""))
        tv2.setText(mSettings.getString(APP_PREFERENCES_AGE, ""))

        //Обработчик кнопки Сгенерировать
        val btnToGenerate: Button = findViewById(R.id.btnGenerate)
        btnToGenerate.setOnClickListener {
            var myNewInt: Int = autoCompleteTextView.text.toString().toInt()

            //Получаем нажатые чипсы
            val ids = chpgr1.getCheckedChipIds()
            val titles = mutableListOf<CharSequence>()
            ids.forEach { id ->
                titles.add(chpgr1.findViewById<Chip>(id).text)
                ++ countOfSelectedChips1
            }
            //Создаем массив с нажатыми чипсами
            if (titles.isNotEmpty()) {
                titles.joinToString()
                y = 0
            } else {
                y = 1
            }

            //Цикл генерации пароля
            for (i in 1..myNewInt) {
                var x = (1..2).random()
                when (x) {
                    1 -> when (y) {0 -> listPass = ((listPass + titles.get((0..countOfSelectedChips1-1).random())) as MutableList<Int>)
                            1 -> listPass = (listPass + (0..9).random()).toMutableList() }
                    2 -> listPass = (listPass + (0..9).random()).toMutableList()
                }
            }

            passitog.setText(listPass.joinToString(
                prefix = "",
                separator = "",
                limit = myNewInt,
                postfix = "",
                truncated = "",
            ))
            listPass.clear()
            countOfSelectedChips1 = 0
        }


        //Обработчик кнопки Скопировать
        val btnToCopy: Button = findViewById(R.id.btnCopy)
        btnToCopy.setOnClickListener {
            val clipboard: ClipboardManager =
                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(label.toString(), passitog.text.toString())
            clipboard.setPrimaryClip(clip)


        }


        val btnToSave: Button = findViewById(R.id.btnSave)
        btnToSave.setOnClickListener {

            var Nmme = NameOfPass.text.toString();
            var psswrd = passitog.text.toString()
            editor.putString(APP_PREFERENCES_NAME, Nmme);
            editor.putString(APP_PREFERENCES_AGE, psswrd);
            ///++countOfSavedPasswords
            //editor.putString(APP_PREFERENCES_COUNT, countOfSavedPasswords.toString());
            editor.apply();

            if(mSettings.contains(APP_PREFERENCES_NAME)) {
                tv1.setText(mSettings.getString(APP_PREFERENCES_NAME, ""));
            }
            if(mSettings.contains(APP_PREFERENCES_AGE)) {
                tv2.setText(mSettings.getString(APP_PREFERENCES_AGE, passitog.text.toString()));
            }


        }


        autoCompleteTextView.setAdapter(arrayAdapter)
        //val items = arrayOf("Item 1", "Item 2", "Item 3", "Item 4")
        //val textField: TextInputLayout = findViewById(R.id.filledTextCount) as TextInputLayout
        //(textField.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)



        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}