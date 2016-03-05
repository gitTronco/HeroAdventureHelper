package com.troncodroide.heroadventurehelper.utils;

import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;

import com.troncodroide.heroadventurehelper.APP;
import com.troncodroide.heroadventurehelper.R;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    public static final int TYPE_PASSWORD = 0;
    public static final int TYPE_EMAIL = 1;
    public static final int TYPE_TEXT = 2;
    public static final int TYPE_CHECKBOX = 3;
    public static final int TYPE_SPINNER = 10;
    public static final int TYPE_CARD = 11;
    public static final int TYPE_CARD_EXPIRED = 12;
    public static final int TYPE_PHONE = 13;
    public static final int TYPE_ZIPCODE = 14;

    public static final int NO_ERRORS = 0;
    public static final int ERROR_EMPTY = 1;
    public static final int ERROR_FORMAT = 2;
    public static final int ERROR_LENGTH = 3;
    public static final int ERROR_DIFERENTS = 4;
    public static final int ERROR_UNCHECKED = 4;
    public static final int ERROR_UNRATING = 5;
    public static final int ERROR_INCORRECT_PASS = 6;

    public static void restoreTextByTag(TextView view) {
        if (view.getTag() != null) {
            view.setText(view.getTag().toString());
        }
    }

    public static void saveTextByTag(TextView view) {
        view.setTextColor(APP.GetColor(R.color.colorAccent));
        if (view.getTag() == null) {
            view.setTag(view.getText().toString());
        }
    }

    public static int validatePasswordUser(TextView view, String textTovalidate, String currentPassword, ValidateCorrectPassListener validateListener) {
        restoreTextByTag(view);
        if (textTovalidate == null || textTovalidate.trim().length() == 0) {
            saveTextByTag(view);
            if (validateListener != null) {
                validateListener.onErrorEmpty(view);
            }
            return ERROR_EMPTY;
        } else {
            if (textTovalidate.trim().length() > 7 && new PasswordValidator().validate(textTovalidate)) {

                //Todo  modificar esta comprobación en caso de que se añada la codificación a la password
                if (currentPassword.equals(textTovalidate)) {
                    if (validateListener != null) {
                        validateListener.onValidateSucces(view);
                    }
                    return NO_ERRORS;
                } else {
                    saveTextByTag(view);
                    if (validateListener != null) {
                        validateListener.onErrorIncorrectPassword(view);
                    }
                    return ERROR_INCORRECT_PASS;
                }


            } else if (textTovalidate.trim().length() <= 7) {
                saveTextByTag(view);

                if (validateListener != null) {
                    validateListener.onErrorLength(view);
                }
                return ERROR_LENGTH;
            } else {
                saveTextByTag(view);

                if (validateListener != null) {
                    validateListener.onErrorFormat(view, TYPE_PASSWORD);
                }
                return ERROR_FORMAT;

            }
        }

    }


    public static int validateCardExpired(TextView view, int month, int year, ValidateListener validateListener) {
        restoreTextByTag(view);
        final Calendar c = Calendar.getInstance();
        int currentYear = c.get(Calendar.YEAR);
        int currentMonth = c.get(Calendar.MONTH) + 1;

        if (year == currentYear && month < currentMonth) {
            saveTextByTag(view);
            if (validateListener != null) {
                validateListener.onErrorFormat(view, TYPE_CARD_EXPIRED);
            }
            return ERROR_FORMAT;
        } else {
            if (validateListener != null) {
                validateListener.onValidateSucces(view);
            }
            return NO_ERRORS;

        }


    }


    public static int validateMaxLenght(TextView view, String textTovalidate, int length, ValidateListener validateListener) {
        restoreTextByTag(view);
        if (textTovalidate == null || textTovalidate.length() > length) {
            saveTextByTag(view);
            if (validateListener != null) {
                validateListener.onErrorLength(view);
            }
            return ERROR_LENGTH;
        }
        if (validateListener != null) {
            validateListener.onValidateSucces(view);
        }
        return NO_ERRORS;


    }

    public static int validate(TextView view, String textTovalidate, int validationType, ValidateListener validateListener) {

        restoreTextByTag(view);
        if (textTovalidate == null || textTovalidate.trim().length() == 0) {
            saveTextByTag(view);
            if (validateListener != null) {
                validateListener.onErrorEmpty(view);
            }
            return ERROR_EMPTY;
        } else {
            textTovalidate = textTovalidate.trim();
            switch (validationType) {
                case TYPE_SPINNER:
                    if (Integer.parseInt(textTovalidate) == 0) {
                        saveTextByTag(view);
                        if (validateListener != null) {
                            validateListener.onErrorEmpty(view);
                        }
                        return ERROR_EMPTY;

                    } else {
                        if (validateListener != null) {
                            validateListener.onValidateSucces(view);
                        }
                        return NO_ERRORS;
                    }


                case TYPE_EMAIL:
                    if (new EmailValidator().validate(textTovalidate)) {

                        if (validateListener != null) {
                            validateListener.onValidateSucces(view);
                        }
                        return NO_ERRORS;
                    } else {
                        saveTextByTag(view);

                        if (validateListener != null) {
                            validateListener.onErrorFormat(view, validationType);
                        }
                        return ERROR_FORMAT;
                    }
                case TYPE_PASSWORD:
                    if (textTovalidate.trim().length() > 7 && new PasswordValidator().validate(textTovalidate)) {

                        if (validateListener != null) {
                            validateListener.onValidateSucces(view);
                        }
                        return NO_ERRORS;
                    } else if (textTovalidate.trim().length() <= 7) {
                        saveTextByTag(view);

                        if (validateListener != null) {
                            validateListener.onErrorLength(view);
                        }
                        return ERROR_LENGTH;
                    } else {
                        saveTextByTag(view);

                        if (validateListener != null) {
                            validateListener.onErrorFormat(view, validationType);
                        }
                        return ERROR_FORMAT;

                    }

                case TYPE_PHONE:
                    if (new PhoneValidator().validate(textTovalidate)) {

                        if (validateListener != null) {
                            validateListener.onValidateSucces(view);
                        }
                        return NO_ERRORS;
                    } else {
                        saveTextByTag(view);

                        if (validateListener != null) {
                            validateListener.onErrorFormat(view, validationType);
                        }
                        return ERROR_FORMAT;
                    }
                case TYPE_ZIPCODE:
                    if (textTovalidate.length() < 7 && textTovalidate.length() > 4) {

                        if (validateListener != null) {
                            validateListener.onValidateSucces(view);
                        }
                        return NO_ERRORS;
                    } else {
                        saveTextByTag(view);

                        if (validateListener != null) {
                            validateListener.onErrorLength(view);
                        }
                        return ERROR_LENGTH;
                    }

            }
        }

        if (validateListener != null) {
            validateListener.onValidateSucces(view);
        }
        return NO_ERRORS;
    }

    public static int validate(CheckBox view, CheckBoxValidateListener validateListener) {
        restoreTextByTag(view);
        if (!view.isChecked()) {
            saveTextByTag(view);
            if (validateListener != null) {
                validateListener.onValidateError(view);
            }
            return ERROR_UNCHECKED;
        }
        if (validateListener != null) {
            validateListener.onValidateSucces(view);
        }
        return NO_ERRORS;
    }


    public static int validate(RatingBar view, RatingBarValidateListener validateListener) {
        if ((int) view.getRating() == 0) {

            if (validateListener != null) {
                validateListener.onValidateError(view);
            }
            return ERROR_UNRATING;
        }
        if (validateListener != null) {
            validateListener.onValidateSucces(view);
        }
        return NO_ERRORS;
    }


    public static int validateSame(TextView view, String textTovalidate, String textTovalidate2, ValidateListener validateListener) {
        textTovalidate = textTovalidate.trim();
        textTovalidate2 = textTovalidate2.trim();
        restoreTextByTag(view);
        if (textTovalidate.contentEquals(textTovalidate2)) {
            if (validateListener != null) {
                validateListener.onValidateSucces(view);
            }
            return NO_ERRORS;
        } else {
            if (validateListener != null) {
                saveTextByTag(view);
                validateListener.onErrorFormat(view, ERROR_DIFERENTS);
            }
            return ERROR_DIFERENTS;
        }

    }


    public static class EmailValidator {

        private Pattern pattern;
        private Matcher matcher;

        private static final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        public EmailValidator() {
            pattern = Pattern.compile(EMAIL_PATTERN);
        }

        /**
         * Validate hex with regular expression
         *
         * @param hex hex for validation
         * @return true valid hex, false invalid hex
         */
        public boolean validate(final String hex) {

            matcher = pattern.matcher(hex);
            return matcher.matches();

        }
    }


    public static class PhoneValidator {

        private Pattern pattern;
        private Matcher matcher;

        private static final String PHONE_PATTERN = "\\d{10}";

        public PhoneValidator() {
            pattern = Pattern.compile(PHONE_PATTERN);
        }

        /**
         * Validate hex with regular expression
         *
         * @param hex hex for validation
         * @return true valid hex, false invalid hex
         */
        public boolean validate(final String hex) {

            matcher = pattern.matcher(hex);
            return matcher.matches();

        }
    }

    public static class PasswordValidator {

        private Pattern pattern;
        private Matcher matcher;

        //` ~ ! @ # $ % ^ & * ( ) _ - + = { } [ ] \ | : ; " ' < > , . ? /
        //private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[`~!@#$%^&*()_\\-+={}\\[\\]\\|:;\"'<>,.?/]).{7,20})";
        //new patter to only check if no blank spaces
        private static final String PASSWORD_PATTERN = "\\S{7,20}";

        public PasswordValidator() {
            pattern = Pattern.compile(PASSWORD_PATTERN);
        }

        /**
         * Validate password with regular expression
         *
         * @param password password for validation
         * @return true valid password, false invalid password
         */
        public boolean validate(final String password) {

            matcher = pattern.matcher(password);
            return matcher.matches();

        }
    }

    public interface ValidateListener {
        void onValidateSucces(TextView errorTarget);

        void onErrorLength(TextView errorTarget);

        void onErrorFormat(TextView errorTarget, int validationType);

        void onErrorEmpty(TextView errorTarget);
    }

    public interface ValidateCorrectPassListener extends ValidateListener {
        void onErrorIncorrectPassword(TextView errorTarget);
    }


    public interface CheckBoxValidateListener {
        void onValidateSucces(TextView errorTarget);

        void onValidateError(TextView errorTarget);
    }


    public interface RatingBarValidateListener {
        void onValidateSucces(RatingBar errorTarget);

        void onValidateError(RatingBar errorTarget);
    }
}
