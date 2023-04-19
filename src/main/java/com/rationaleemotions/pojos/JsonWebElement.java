package com.rationaleemotions.pojos;

import com.google.common.collect.Maps;
import com.rationaleemotions.internal.JvmArgs;
import com.rationaleemotions.internal.parser.pojos.Element;
import com.rationaleemotions.internal.parser.pojos.Locale;
import com.rationaleemotions.internal.parser.pojos.Wait;
import com.rationaleemotions.page.WebElementType;
import com.rationaleemotions.utils.StringUtils;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

public final class JsonWebElement {
    static final int DEFAULT_WAIT_TIME = JvmArgs.DEFAULT_WAIT_TIME.asInt();
    static final String ATTRIBUTE_IS_MISSING = " attribute is missing.";
    private String name;
    private boolean isDynamic;
    private Map<String, By> locationStrategy = Maps.newHashMap();
    private Map<String, String> locationStrategy1 = Maps.newHashMap();
    private String defaultLocale;
    private WebElementType type;
    private Wait wait;
    private List<Locale> locales;

    public List<Locale> getLocales() {
        return locales;
    }


    public void setLocales(List<Locale> locales) {
        this.locales = locales;
    }

    private JsonWebElement() {
        //Defeat instantiation
    }

    public Wait getWait() {
        return wait;
    }

    public WebElementType getType() {
        return type;
    }

    String getName() {
        return name;
    }

    public boolean isDynamic() {
        return isDynamic;
    }

    public By getLocationStrategy(String whichLocale) {
        checkArgument(StringUtils.isNotBlank(whichLocale), "Querying locale cannot be empty (or) null.");
        checkState(locationStrategy.containsKey(defaultLocale), "Un-recognized default locale [" + defaultLocale + "]"
                + " provided.");
        if (locationStrategy.containsKey(whichLocale)) {
            return locationStrategy.get(whichLocale);
        }
        return locationStrategy.get(defaultLocale);
    }

    public String getLocationStrategy1(String whichLocale) {
        checkArgument(StringUtils.isNotBlank(whichLocale), "Querying locale cannot be empty (or) null.");
        checkState(locationStrategy.containsKey(defaultLocale), "Un-recognized default locale [" + defaultLocale + "]"
                + " provided.");
        if (locationStrategy.containsKey(whichLocale)) {
            return locationStrategy1.get(whichLocale);
        }
        return locationStrategy1.get(defaultLocale);
    }

    static JsonWebElement newElement(Element element, String defaultLocale) {
        element.validate();
        JsonWebElement jsonWebElement = new JsonWebElement();
        jsonWebElement.name = element.getName();
        jsonWebElement.isDynamic = element.isDynamic();

        jsonWebElement.locales = element.getLocales();

        List<LocaleDefinition> definitions = LocaleDefinition.newDefinition(element.getLocales());
        definitions.forEach(localeDefinition -> {
            System.out.println(localeDefinition.getLocale());
            System.out.println(localeDefinition.getLocationStrategy());
            jsonWebElement.locationStrategy.put(localeDefinition.getLocale(), localeDefinition.getLocationStrategy());
        });
        jsonWebElement.defaultLocale = defaultLocale;
        if (element.getWait() != null && element.getWait().isValid()) {
            jsonWebElement.wait = element.getWait();
        }
        jsonWebElement.type = element.getType();
        return jsonWebElement;
    }

    static JsonWebElement newElement(Element element, String defaultLocale, String fieldName, Object... args) {
        element.validate();
        JsonWebElement jsonWebElement = new JsonWebElement();
        jsonWebElement.name = element.getName();

        // Apply string formatting for the provided arguments on string placeholder
        List<Locale> locales = element.getLocales();
        if (jsonWebElement.name.equalsIgnoreCase(fieldName)) {
            for (Locale locale : element.getLocales()) {
                locale.setLocator(String.format(locale.getLocator(), args));
            }
        }

        List<LocaleDefinition> definitions = LocaleDefinition.newDefinition(locales, fieldName, args);
        definitions.forEach(localeDefinition -> {
            System.out.println(localeDefinition.getLocationStrategy());
            System.out.println(localeDefinition.getLocale());
            jsonWebElement.locationStrategy.put(localeDefinition.getLocale(), localeDefinition.getLocationStrategy());
                });
        jsonWebElement.defaultLocale = defaultLocale;
        if (element.getWait() != null && element.getWait().isValid()) {
            jsonWebElement.wait = element.getWait();
        }
        jsonWebElement.type = element.getType();
        return jsonWebElement;
    }

}
