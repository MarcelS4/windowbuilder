/*******************************************************************************
 * Copyright (c) 2011 Google, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Google, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.wb.internal.ercp.preferences;

import org.eclipse.wb.internal.core.preferences.IPreferenceConstants;
import org.eclipse.wb.internal.ercp.ToolkitProvider;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Initializer for eRCP preferences.
 * 
 * @author scheglov_ke
 * @coverage ercp.preferences
 */
public final class PreferenceInitializer extends AbstractPreferenceInitializer {
  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore preferences = ToolkitProvider.DESCRIPTION.getPreferences();
    // general
    preferences.setDefault(IPreferenceConstants.P_GENERAL_HIGHLIGHT_CONTAINERS, true);
    preferences.setDefault(IPreferenceConstants.P_GENERAL_TEXT_SUFFIX, true);
    preferences.setDefault(IPreferenceConstants.P_GENERAL_IMPORTANT_PROPERTIES_AFTER_ADD, false);
    preferences.setDefault(IPreferenceConstants.P_GENERAL_DIRECT_EDIT_AFTER_ADD, true);
    preferences.setDefault(IPreferenceConstants.P_GENERAL_DEFAULT_TOP_WIDTH, 450);
    preferences.setDefault(IPreferenceConstants.P_GENERAL_DEFAULT_TOP_HEIGHT, 300);
    // SWT specific
    preferences.setDefault(
        org.eclipse.wb.internal.swt.preferences.IPreferenceConstants.P_USE_RESOURCE_MANAGER,
        true);
    preferences.setDefault(IPreferenceConstants.P_STYLE_PROPERTY_CASCADE_POPUP, false);
    // variable names
    {
      preferences.setDefault(
          IPreferenceConstants.P_VARIABLE_TEXT_MODE,
          IPreferenceConstants.V_VARIABLE_TEXT_MODE_DEFAULT);
      preferences.setDefault(
          IPreferenceConstants.P_VARIABLE_TEXT_TEMPLATE,
          "${class_acronym}${text}");
      preferences.setDefault(IPreferenceConstants.P_VARIABLE_TEXT_WORDS_LIMIT, 3);
    }
    // NLS
    {
      preferences.setDefault(IPreferenceConstants.P_NLS_AUTO_EXTERNALIZE, true);
      preferences.setDefault(IPreferenceConstants.P_NLS_KEY_QUALIFIED_TYPE_NAME, false);
      preferences.setDefault(IPreferenceConstants.P_NLS_KEY_AS_VALUE_PREFIX, "*");
    }
    // layout
    preferences.setDefault(IPreferenceConstants.P_LAYOUT_OF_PARENT, false);
    preferences.setDefault(
        org.eclipse.wb.internal.swt.preferences.IPreferenceConstants.P_LAYOUT_NAME_TEMPLATE,
        "${layoutAcronym}_${compositeName}");
    preferences.setDefault(
        org.eclipse.wb.internal.swt.preferences.IPreferenceConstants.P_LAYOUT_DATA_NAME_TEMPLATE,
        "${dataAcronym}_${controlName}");
    // GridLayout
    {
      preferences.setDefault(
          org.eclipse.wb.internal.swt.model.layout.grid.IPreferenceConstants.P_ENABLE_GRAB,
          true);
      preferences.setDefault(
          org.eclipse.wb.internal.swt.model.layout.grid.IPreferenceConstants.P_ENABLE_RIGHT_ALIGNMENT,
          true);
    }
  }
}
