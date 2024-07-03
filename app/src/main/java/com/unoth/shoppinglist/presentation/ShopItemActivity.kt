package com.unoth.shoppinglist.presentation

import android.content.Context
import android.content.Intent
import android.content.Intent.parseIntent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.unoth.shoppinglist.R
import com.unoth.shoppinglist.domain.ShopItem

class ShopItemActivity : AppCompatActivity() {
    /*
        private lateinit var viewmodel: ShopItemViewModel

        private lateinit var tilName: TextInputLayout
        private lateinit var tilCount: TextInputLayout
        private lateinit var etName: TextInputEditText
        private lateinit var etCount: TextInputEditText
        private lateinit var btnSave: Button
    */
    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_shop_item)
/*        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        parseIntent()
        launchRightMode()
        /*   viewmodel = ViewModelProvider(this)[ShopItemViewModel::class.java]
           initViews()

           observeViewModel()
           addTextChangedListener()*/
    }

    private fun launchRightMode() {
        val fragment = when (screenMode) {
            MODE_ADD -> ShopItemFragment.newInstanceAddItem()
            MODE_EDIT -> ShopItemFragment.newInstanceEditItem(shopItemId)
            else -> throw RuntimeException("Unknown screen mode $screenMode")
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.shop_item_container, fragment)
            .commit()
    }

    /*
        private fun initViews() {
            tilName = findViewById(R.id.til_name)
            tilCount = findViewById(R.id.til_count)
            etName = findViewById(R.id.et_name)
            etCount = findViewById(R.id.et_count)
            btnSave = findViewById(R.id.btn_save)
        }



        private fun observeViewModel() {
            viewmodel.errorInputCount.observe(this) {
                val message = if (it) {
                    getString(R.string.error_input_count)
                } else {
                    null
                }
                tilCount.error = message
            }
            viewmodel.errorInputName.observe(this) {
                val message = if (it) {
                    getString(R.string.error_input_name)
                } else {
                    null
                }
                tilName.error = message
            }
            viewmodel.shouldCloseScreen.observe(this) {
                finish()
            }
        }

        private fun addTextChangedListener() {
            etName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewmodel.resetErrorInputName()
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })

            etCount.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewmodel.resetErrorInputCount()
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
        }

        private fun launchAddMode() {
            btnSave.setOnClickListener {
                viewmodel.addShopItem(etName.text?.toString(), etCount.text?.toString())
            }
        }

        private fun launchEditMode() {
            viewmodel.getShopItemId(shopItemId)
            viewmodel.shopItem.observe(this) {
                etName.setText(it.name)
                etCount.setText(it.count.toString())
            }
            btnSave.setOnClickListener {
                viewmodel.editShopItem(etName.text?.toString(), etCount.text?.toString())
            }
        }
    */
    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode ia absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Unknown screen mode: $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException("Param shop item id ia absent")
            }
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
        }
    }


    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return intent
        }
    }
}