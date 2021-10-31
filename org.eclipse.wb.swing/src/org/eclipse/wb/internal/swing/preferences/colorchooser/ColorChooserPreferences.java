package org.eclipse.wb.internal.swing.preferences.colorchooser;

import org.eclipse.wb.internal.swing.model.ModelMessages;
import org.eclipse.wb.internal.swing.model.property.editor.color.ColorPropertyEditor;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import java.util.HashMap;
import java.util.Map;

import swing2swt.layout.BorderLayout;

public class ColorChooserPreferences extends PreferencePage implements IWorkbenchPreferencePage {
  Preferences preferences =
      InstanceScope.INSTANCE.getNode(IColorChooserPreferenceConstants.PREFERENCE_NODE);
  Preferences prefs = preferences.node(IColorChooserPreferenceConstants.PREFERENCE_NODE_1);
  Button cbxIncludeCustomColor;
  Button cbxIncludeSystemColor;
  Button cbxIncludeAwtColors;
  Button cbxIncludeSwingColors;
  Button cbxIncludeNamedColors;
  Button cbxIncludeWebSafeColors;
  final Map<String, Boolean> defaultPreferences = new HashMap<String, Boolean>() {
    private static final long serialVersionUID = 1L;
    {
      put(IColorChooserPreferenceConstants.P_CUSTOM_COLORS, true);
      put(IColorChooserPreferenceConstants.P_SYSTEM_COLORS, true);
      put(IColorChooserPreferenceConstants.P_AWT_COLORS, true);
      put(IColorChooserPreferenceConstants.P_SWING_COLORS, true);
      put(IColorChooserPreferenceConstants.P_NAMED_COLORS, true);
      put(IColorChooserPreferenceConstants.P_WEB_SAFE_COLORS, true);
    }
  };
  private Group grpInclude;

  public ColorChooserPreferences() {
  }

  public ColorChooserPreferences(String title) {
    super(title);
  }

  /**
   * This annotation is requried to instruct WindowBuilder which constructor to run
   *
   * @wbp.parser.constructor
   */
  public ColorChooserPreferences(String title, ImageDescriptor image) {
    super(title, image);
    setPersistedPreferencesToUI();
  }

  public void init(IWorkbench workbench) {
  }

  @Override
  protected Control createContents(Composite parent) {
    Composite container = new Composite(parent, SWT.NONE);
    container.setLayout(new BorderLayout(0, 0));
    Composite composite = new Composite(container, SWT.NONE);
    composite.setLayoutData(BorderLayout.NORTH);
    RowLayout rl_composite = new RowLayout(SWT.HORIZONTAL);
    composite.setLayout(rl_composite);
    grpInclude = new Group(composite, SWT.NONE);
    grpInclude.setText(ModelMessages.ColorPreferenceChooser_Include);
    grpInclude.setLayout(new RowLayout(SWT.VERTICAL));
    cbxIncludeCustomColor = new Button(grpInclude, SWT.CHECK);
    cbxIncludeCustomColor.setText(IColorChooserPreferenceConstants.CUSTOM_COLORS);
    cbxIncludeSystemColor = new Button(grpInclude, SWT.CHECK);
    cbxIncludeSystemColor.setText(IColorChooserPreferenceConstants.SYSTEM_COLORS);
    cbxIncludeAwtColors = new Button(grpInclude, SWT.CHECK);
    cbxIncludeAwtColors.setText(IColorChooserPreferenceConstants.AWT_COLORS);
    cbxIncludeSwingColors = new Button(grpInclude, SWT.CHECK);
    cbxIncludeSwingColors.setText(IColorChooserPreferenceConstants.SWING_COLORS);
    cbxIncludeNamedColors = new Button(grpInclude, SWT.CHECK);
    cbxIncludeNamedColors.setText(IColorChooserPreferenceConstants.NAMED_COLORS);
    cbxIncludeWebSafeColors = new Button(grpInclude, SWT.CHECK);
    cbxIncludeWebSafeColors.setText(IColorChooserPreferenceConstants.WEBSAFE_COLORS);
    setPersistedPreferencesToUI();
    return container;
  }

  //Initialize the check boxes to the persisted preferences
  private void setPersistedPreferencesToUI() {
    cbxIncludeCustomColor.setSelection(
        prefs.getBoolean(
            IColorChooserPreferenceConstants.P_CUSTOM_COLORS,
            defaultPreferences.get(IColorChooserPreferenceConstants.P_CUSTOM_COLORS)));
    cbxIncludeSystemColor.setSelection(
        prefs.getBoolean(
            IColorChooserPreferenceConstants.P_SYSTEM_COLORS,
            defaultPreferences.get(IColorChooserPreferenceConstants.P_SYSTEM_COLORS)));
    cbxIncludeAwtColors.setSelection(
        prefs.getBoolean(
            IColorChooserPreferenceConstants.P_AWT_COLORS,
            defaultPreferences.get(IColorChooserPreferenceConstants.P_AWT_COLORS)));
    cbxIncludeSwingColors.setSelection(
        prefs.getBoolean(
            IColorChooserPreferenceConstants.P_SWING_COLORS,
            defaultPreferences.get(IColorChooserPreferenceConstants.P_SWING_COLORS)));
    cbxIncludeNamedColors.setSelection(
        prefs.getBoolean(
            IColorChooserPreferenceConstants.P_NAMED_COLORS,
            defaultPreferences.get(IColorChooserPreferenceConstants.P_NAMED_COLORS)));
    cbxIncludeWebSafeColors.setSelection(
        prefs.getBoolean(
            IColorChooserPreferenceConstants.P_WEB_SAFE_COLORS,
            defaultPreferences.get(IColorChooserPreferenceConstants.P_WEB_SAFE_COLORS)));
  }

  @Override
  protected void performApply() {
    try {
      prefs.putBoolean(
          IColorChooserPreferenceConstants.P_CUSTOM_COLORS,
          cbxIncludeCustomColor.getSelection());
      prefs.putBoolean(
          IColorChooserPreferenceConstants.P_SYSTEM_COLORS,
          cbxIncludeSystemColor.getSelection());
      prefs.putBoolean(
          IColorChooserPreferenceConstants.P_AWT_COLORS,
          cbxIncludeAwtColors.getSelection());
      prefs.putBoolean(
          IColorChooserPreferenceConstants.P_SWING_COLORS,
          cbxIncludeSwingColors.getSelection());
      prefs.putBoolean(
          IColorChooserPreferenceConstants.P_NAMED_COLORS,
          cbxIncludeNamedColors.getSelection());
      prefs.putBoolean(
          IColorChooserPreferenceConstants.P_WEB_SAFE_COLORS,
          cbxIncludeWebSafeColors.getSelection());
      prefs.flush();
      //This ColorPropertyEditor could be null therefore a check is performed.
      //This method should be in the ColorPropertyEditor itself, but for some reason it seems that
      //org.osgi.service.prefs.Preferences no longer has a propertyChange method.
      if (ColorPropertyEditor.INSTANCE != null) {
        ColorPropertyEditor.reloadColorDialog();
      }
    } catch (BackingStoreException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method is called on the "Apply and Close" button
   */
  @Override
  public boolean performOk() {
    performApply();
    return true;
  }

  //Sets the preferences to the default values
  private void setPreferenceDefaults() {
    prefs.putBoolean(
        IColorChooserPreferenceConstants.P_CUSTOM_COLORS,
        defaultPreferences.get(IColorChooserPreferenceConstants.P_CUSTOM_COLORS));
    prefs.putBoolean(
        IColorChooserPreferenceConstants.P_SYSTEM_COLORS,
        defaultPreferences.get(IColorChooserPreferenceConstants.P_SYSTEM_COLORS));
    prefs.putBoolean(
        IColorChooserPreferenceConstants.P_AWT_COLORS,
        defaultPreferences.get(IColorChooserPreferenceConstants.P_AWT_COLORS));
    prefs.putBoolean(
        IColorChooserPreferenceConstants.P_SWING_COLORS,
        defaultPreferences.get(IColorChooserPreferenceConstants.P_SWING_COLORS));
    prefs.putBoolean(
        IColorChooserPreferenceConstants.P_NAMED_COLORS,
        defaultPreferences.get(IColorChooserPreferenceConstants.P_NAMED_COLORS));
    prefs.putBoolean(
        IColorChooserPreferenceConstants.P_WEB_SAFE_COLORS,
        defaultPreferences.get(IColorChooserPreferenceConstants.P_WEB_SAFE_COLORS));
  }

  @Override
  protected void performDefaults() {
    //Update the UI to the default values
    cbxIncludeCustomColor.setSelection(
        defaultPreferences.get(IColorChooserPreferenceConstants.P_CUSTOM_COLORS));
    cbxIncludeSystemColor.setSelection(
        defaultPreferences.get(IColorChooserPreferenceConstants.P_SYSTEM_COLORS));
    cbxIncludeAwtColors.setSelection(
        defaultPreferences.get(IColorChooserPreferenceConstants.P_AWT_COLORS));
    cbxIncludeSwingColors.setSelection(
        defaultPreferences.get(IColorChooserPreferenceConstants.P_SWING_COLORS));
    cbxIncludeNamedColors.setSelection(
        defaultPreferences.get(IColorChooserPreferenceConstants.P_NAMED_COLORS));
    cbxIncludeWebSafeColors.setSelection(
        defaultPreferences.get(IColorChooserPreferenceConstants.P_WEB_SAFE_COLORS));
    //Update the preferences to the default values
    setPreferenceDefaults();
  }
}
