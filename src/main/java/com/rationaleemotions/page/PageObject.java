package com.rationaleemotions.page;

import com.google.common.collect.Lists;
import com.rationaleemotions.internal.locators.WaitCondition;
import com.rationaleemotions.internal.parser.pojos.Wait;
import com.rationaleemotions.pojos.JsonWebElement;
import com.rationaleemotions.pojos.WebPage;
import com.rationaleemotions.utils.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.List;
import java.util.MissingFormatArgumentException;

/**
 * This class represents the entry point to all page object building.
 * Houses all the logic required for loading a json file, parsing it, initialising location strategies
 * based on the values provided in the json file and also in constructing different html elements.
 */
@SuppressWarnings("unused")
public final class PageObject {
    private final SearchContext context;
    private final String jsonFileSource;
    private WebPage page;
    private String locale;

    /**
     * Helps create a new instance.
     *
     * @param context        - A {@link WebDriver} instance which would be used to query the elements.
     * @param jsonFileSource - The location of the JSON file that contains the elements definition.
     */
    public PageObject(WebDriver context, String jsonFileSource) {
        this.context = context;
        this.jsonFileSource = jsonFileSource;
    }

    /**
     * @param locale - The locale for which the page object is being created.
     * @return - The current {@link PageObject} instance.
     */
    public PageObject forLocale(String locale) {
        this.locale = locale;
        return this;
    }

    /**
     * @param fieldName - The name of the field as defined in the JSON file.
     * @return - A {@link Button} instance.
     */
    public final Button getButton(String fieldName) {
        return getElement(fieldName, Button.class);
    }

    public final Button getButton(String fieldName, Object... args) {
        return getElement(getFormattedLocatorText(fieldName, args), Button.class);
    }

    /**
     * @param fieldName - The name of the field as defined in the JSON file.
     * @return - A {@link List} of {@link Button} instances.
     */
    public final List<Button> getButtons(String fieldName) {
        return getElements(fieldName, Button.class);
    }

    public final List<Button> getButtons(String fieldName, Object... args) {
        return getElements(getFormattedLocatorText(fieldName, args), Button.class);
    }

    /**
     * @param fieldName - The name of the field as defined in the JSON file.
     * @return - A {@link Checkbox} instance.
     */
    public final Checkbox getCheckbox(String fieldName) {
        return getElement(fieldName, Checkbox.class);
    }

    public final Checkbox getCheckbox(String fieldName, Object... args) {
        return getElement(getFormattedLocatorText(fieldName, args), Checkbox.class);
    }

    /**
     * @param fieldName - The name of the field as defined in the JSON file.
     * @return - A {@link List} of {@link Checkbox} instances.
     */
    public final List<Checkbox> getCheckboxes(String fieldName) {
        return getElements(fieldName, Checkbox.class);
    }

    public final List<Checkbox> getCheckboxes(String fieldName, Object... args) {
        return getElements(getFormattedLocatorText(fieldName, args), Checkbox.class);
    }

    /**
     * @param fieldName - The name of the field as defined in the JSON file.
     * @return - A {@link Form} instance.
     */
    public final Form getForm(String fieldName) {
        return getElement(fieldName, Form.class);
    }

    public final Form getForm(String fieldName, Object... args) {
        return getElement(getFormattedLocatorText(fieldName, args), Form.class);
    }

    /**
     * @param fieldName - The name of the field as defined in the JSON file.
     * @return - A {@link List} of {@link Form} instances.
     */
    public final List<Form> getForms(String fieldName) {
        return getElements(fieldName, Form.class);
    }

    public final List<Form> getForms(String fieldName, Object... args) {
        return getElements(getFormattedLocatorText(fieldName, args), Form.class);
    }

    /**
     * This method is used, when dealing with custom html elements which doesn't have the required visualisation
     * built in by our page object model. E.g., for this could include controls such as date pickers for which
     * there are no built in objects provided by this library (similar to {@link Button} or {@link Link} etc.,)
     *
     * @param fieldName - The name of the field as defined in the JSON file.
     * @return - A {@link GenericElement} instance.
     */
    public final GenericElement getGenericElement(String fieldName) {
        return getElement(fieldName, GenericElement.class);
    }

    public final GenericElement getGenericElement(String fieldName, Object... args) {
        return getElement(getFormattedLocatorText(fieldName, args), GenericElement.class);
    }

    /**
     * This method is used, when dealing with custom html elements which doesn't have the required visualisation
     * built in by our page object model. E.g., for this could include controls such as date pickers for which
     * there are no built in objects provided by this library (similar to {@link Button} or {@link Link} etc.,)
     *
     * @param fieldName - The name of the field as defined in the JSON file.
     * @return - A {@link List} of {@link GenericElement} instances.
     */
    public final List<GenericElement> getGenericElements(String fieldName) {
        return getElements(fieldName, GenericElement.class);
    }

    public final List<GenericElement> getGenericElements(String fieldName, Object... args) {
        return getElements(getFormattedLocatorText(fieldName, args), GenericElement.class);
    }

    /**
     * @param fieldName - The name of the field as defined in the JSON file.
     * @return - An {@link Image} object.
     */
    public final Image getImage(String fieldName) {
        return getElement(fieldName, Image.class);
    }

    public final Image getImage(String fieldName, Object... args) {
        return getElement(getFormattedLocatorText(fieldName, args), Image.class);
    }

    /**
     * @param fieldName - The name of the field as defined in the JSON file.
     * @return - A {@link List} of {@link Image} instances.
     */
    public final List<Image> getImages(String fieldName) {
        return getElements(fieldName, Image.class);
    }

    public final List<Image> getImages(String fieldName, Object... args) {
        return getElements(getFormattedLocatorText(fieldName, args), Image.class);
    }

    /**
     * @param fieldName - The name of the field as defined in the JSON file.
     * @return - A {@link Label} instance.
     */
    public final Label getLabel(String fieldName) {
        return getElement(fieldName, Label.class);
    }

    public final Label getLabel(String fieldName, Object... args) {
        String str = getFormattedLocatorText(fieldName, args);
        System.out.println(str);
        return getElement(getFormattedLocatorText(fieldName, args), Label.class);
    }

    /**
     * @param fieldName - The name of the field as defined in the JSON file.
     * @return - A {@link List} of {@link Label} instances.
     */
    public final List<Label> getLabels(String fieldName) {
        return getElements(fieldName, Label.class);
    }

    public final List<Label> getLabels(String fieldName, Object... args) {
        return getElements(getFormattedLocatorText(fieldName, args), Label.class);
    }

    /**
     * @param fieldName - The name of the field as defined in the JSON file.
     * @return - A {@link Link} instance.
     */
    public final Link getLink(String fieldName) {
        return getElement(fieldName, Link.class);
    }

    public final Link getLink(String fieldName, Object... args) {
        return getElement(getFormattedLocatorText(fieldName, args), Link.class);
    }

    /**
     * @param fieldName - The name of the field as defined in the JSON file.
     * @return - A {@link List} of {@link Link} instances.
     */
    public final List<Link> getLinks(String fieldName) {
        return getElements(fieldName, Link.class);
    }

    public final List<Link> getLinks(String fieldName, Object... args) {
        return getElements(getFormattedLocatorText(fieldName, args), Link.class);
    }

    /**
     * @param fieldName - The name of the field as defined in the JSON file.
     * @return - A {@link RadioButton} instance.
     */
    public final RadioButton getRadioButton(String fieldName) {
        return getElement(fieldName, RadioButton.class);
    }

    public final RadioButton getRadioButton(String fieldName, Object... args) {
        return getElement(getFormattedLocatorText(fieldName, args), RadioButton.class);
    }

    /**
     * @param fieldName - The name of the field as defined in the JSON file.
     * @return - A {@link List} of {@link RadioButton} instances.
     */
    public final List<RadioButton> getRadioButtons(String fieldName) {
        return getElements(fieldName, RadioButton.class);
    }

    public final List<RadioButton> getRadioButtons(String fieldName, Object... args) {
        return getElements(getFormattedLocatorText(fieldName, args), RadioButton.class);
    }

    /**
     * @param fieldName - The name of the field as defined in the JSON file.
     * @return - A {@link SelectList} instance.
     */
    public final SelectList getSelectList(String fieldName) {
        return getElement(fieldName, SelectList.class);
    }

    public final SelectList getSelectList(String fieldName, Object... args) {
        return getElement(getFormattedLocatorText(fieldName, args), SelectList.class);
    }

    /**
     * @param fieldName - The name of the field as defined in the JSON file.
     * @return - A {@link List} of {@link SelectList} instances.
     */
    public final List<SelectList> getSelectLists(String fieldName) {
        return getElements(fieldName, SelectList.class);
    }

    public final List<SelectList> getSelectLists(String fieldName, Object... args) {
        return getElements(getFormattedLocatorText(fieldName, args), SelectList.class);
    }

    /**
     * @param fieldName - The name of the field as defined in the JSON file.
     * @return - A {@link TextField} instance.
     */
    public final TextField getTextField(String fieldName) {
        return getElement(fieldName, TextField.class);
    }

    public final TextField getTextField(String fieldName, Object... args) {
        return getElement(fieldName, TextField.class, args);
    }

    /**
     * @param fieldName - The name of the field as defined in the JSON file.
     * @return - A {@link List} of {@link TextField} instances.
     */
    public final List<TextField> getTextFields(String fieldName) {
        return getElements(fieldName, TextField.class);
    }

    public final List<TextField> getTextFields(String fieldName, Object... args) {
        return getElements(getFormattedLocatorText(fieldName, args), TextField.class);
    }

    private String getLocale() {
        if (StringUtils.isBlank(locale)) {
            locale = page.getDefaultLocale();
        }
        return locale;
    }

    private <E> E getElement(String fieldName, Class<E> clazz) {
        WebElement webElement = newRawElement(getJsonWebElement(fieldName)).getWebElement();
        return newInstance(clazz, webElement);
    }

    private <E> E getElement(String fieldName, Class<E> clazz, Object... args) {
        WebElement webElement = newRawElement(getJsonWebElement(fieldName, args)).getWebElement();
        return newInstance(clazz, webElement);
    }

    private <E> List<E> getElements(String fieldName, Class<E> clazz) {
        List<WebElement> webElements = newRawElement(getJsonWebElement(fieldName)).getWebElements();
        List<E> elements = Lists.newArrayList();
        for (WebElement webElement : webElements) {
            E e = newInstance(clazz, webElement);
            elements.add(e);
        }
        return elements;
    }

    private void initLazily() {
        if (page != null) {
            return;
        }
        synchronized (this) {
            if (page != null) {
                return;
            }
            page = WebPage.getPage(jsonFileSource);
        }
    }

    private JsonWebElement getJsonWebElement(String fieldName) {
        if (StringUtils.isBlank(fieldName)) {
            throw new IllegalArgumentException("A field name cannot be null (or) empty");
        }
        initLazily();
        JsonWebElement element = page.getWebElement(fieldName);
        if (element == null) {
            throw new IllegalArgumentException("Unable to locate element [" + fieldName +
                    "] in the file [" + this.jsonFileSource + "]");
        }
        return element;
    }

    private JsonWebElement getJsonWebElement(String fieldName, Object... args) {
        if (StringUtils.isBlank(fieldName)) {
            throw new IllegalArgumentException("A field name cannot be null (or) empty");
        }
        initLazily(fieldName, args);
        JsonWebElement element = page.getWebElement(fieldName);
        if (element == null) {
            throw new IllegalArgumentException("Unable to locate element [" + fieldName +
                    "] in the file [" + this.jsonFileSource + "]");
        }
        return element;
    }

    private void initLazily(String fieldName, Object... args) {
        if (page != null) {
            return;
        }
        synchronized (this) {
            if (page != null) {
                return;
            }
            page = WebPage.getPage(jsonFileSource, fieldName, args);
        }
    }
    private RawElement newRawElement(JsonWebElement element) {
        DecoratedSearchContext context = new DecoratedSearchContext(this.context, element.getWait());
        return new RawElement(context, element, getLocale());
    }

    @SuppressWarnings("unchecked")
    private static <E> E newInstance(Class<E> clazz, WebElement webElement) {
        try {
            Constructor<?> constructor = Class.forName(clazz.getName()).getDeclaredConstructor(WebElement.class);
            return (E) constructor.newInstance(webElement);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
                 | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    private static class DecoratedSearchContext implements SearchContext {
        private final SearchContext context;
        private final Wait wait;

        DecoratedSearchContext(SearchContext context, Wait wait) {
            this.context = context;
            this.wait = wait;
        }

        @Override
        public List<WebElement> findElements(By by) {
            if (wait == null || !wait.isElementsConditionValid()) {
                return context.findElements(by);
            }
            WaitCondition waitCondition = wait.getWaitCondition();
            ExpectedCondition<List<WebElement>> condition = waitCondition.elements(by);
            return applyToList(condition);
        }

        @Override
        public WebElement findElement(By by) {
            if (wait == null || !wait.isElementConditionValid()) {
                return context.findElement(by);
            }
            WaitCondition waitCondition = wait.getWaitCondition();
            ExpectedCondition<WebElement> condition = waitCondition.element(by);
            return apply(condition);
        }

        private WebElement apply(ExpectedCondition<WebElement> condition) {
            return new WebDriverWait((WebDriver) this.context, Duration.ofSeconds(wait.getDuration())).until(condition);
        }

        private List<WebElement> applyToList(ExpectedCondition<List<WebElement>> condition) {
            return new WebDriverWait((WebDriver) this.context, Duration.ofSeconds(wait.getDuration())).until(condition);
        }
    }

    private String getFormattedLocatorText(String format, Object... args) {
        String formattedText = "";
        try {
            formattedText = String.format(format, args);
        } catch (MissingFormatArgumentException e) {
            return String.format("Please provide correct number of arguments with locator %s", format);
        }
        return formattedText;
    }
}
