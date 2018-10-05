package com.ductuandt3.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.braintreepayments.cardform.OnCardFormSubmitListener;
import com.braintreepayments.cardform.utils.CardType;
import com.braintreepayments.cardform.view.CardEditText;
import com.braintreepayments.cardform.view.CardForm;
import com.braintreepayments.cardform.view.SupportedCardTypesView;
import com.ductuandt3.myapplication.entity.CardEntity;
import com.ductuandt3.myapplication.sqlLite.DBCard;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BaseCardFormActivity extends AppCompatActivity implements OnCardFormSubmitListener,
        CardEditText.OnCardTypeChangedListener {

    private static final CardType[] SUPPORTED_CARD_TYPES = { CardType.VISA, CardType.MASTERCARD, CardType.DISCOVER,
                CardType.AMEX, CardType.DINERS_CLUB, CardType.JCB, CardType.MAESTRO, CardType.UNIONPAY };

    private SupportedCardTypesView mSupportedCardTypesView;

    DBCard dbCard = new DBCard(this);

    protected CardForm mCardForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_form);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mSupportedCardTypesView = findViewById(R.id.supported_card_types);
        mSupportedCardTypesView.setSupportedCardTypes(SUPPORTED_CARD_TYPES);

        mCardForm = findViewById(R.id.card_form);
        mCardForm.cardRequired(true)
                .maskCardNumber(true)
                .maskCvv(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("Make sure SMS is enabled for this mobile number")
                .actionLabel(getString(R.string.purchase))
                .setup(this);
        mCardForm.setOnCardFormSubmitListener(this);
        mCardForm.setOnCardTypeChangedListener(this);

        ImageButton mImgCamera = (ImageButton) findViewById(R.id.imageButton_camera);
        ImageButton mImgClose = (ImageButton) findViewById(R.id.imageButton_back);
        Button mButonAddCard = (Button) findViewById(R.id.cardForm_button);

        mImgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCardForm.scanCard(BaseCardFormActivity.this);
            }
        });

        mImgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mButonAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardEntity cardEntity = createCard();
                if (cardEntity != null){
                    dbCard.addCard(cardEntity);
                    Intent intent = new Intent(BaseCardFormActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
    }

    private CardEntity createCard(){
        //get time current
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());
        String  CardNumber = mCardForm.getCardNumber();
        String  ExpirationMonth = mCardForm.getExpirationMonth();
        String  ExpirationYear = mCardForm.getExpirationYear();
        String  Cvv = mCardForm.getCvv();
        String  PostalCode = mCardForm.getPostalCode();
        String  CountryCode = mCardForm.getCountryCode();
        String  MobileNumber = mCardForm.getMobileNumber();
        String  DateCurrency = strDate;
        CardEntity cardEntity = new CardEntity(CardNumber, ExpirationMonth, ExpirationYear, Cvv, PostalCode, CountryCode, MobileNumber, DateCurrency);
        return cardEntity;
    }

    @Override
    public void onCardTypeChanged(CardType cardType) {
        if (cardType == CardType.EMPTY) {
            mSupportedCardTypesView.setSupportedCardTypes(SUPPORTED_CARD_TYPES);
        } else {
            mSupportedCardTypesView.setSelected(cardType);
        }
    }

    @Override
    public void onCardFormSubmit() {
        if (mCardForm.isValid()) {
            Toast.makeText(this, R.string.valid, Toast.LENGTH_SHORT).show();
        } else {
            mCardForm.validate();
            Toast.makeText(this, R.string.invalid, Toast.LENGTH_SHORT).show();
        }
    }
}
