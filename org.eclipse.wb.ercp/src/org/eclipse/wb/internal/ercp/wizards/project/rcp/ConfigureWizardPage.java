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
package org.eclipse.wb.internal.ercp.wizards.project.rcp;

import com.google.common.collect.Lists;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.dialogs.PreferencesUtil;

import org.apache.commons.lang.ArrayUtils;

import java.util.List;

/**
 * {@link WizardPage} that forces eRCP configuring.
 * 
 * @author scheglov_ke
 * @coverage ercp.wizards.ui
 */
public final class ConfigureWizardPage extends WizardPage {
  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  public ConfigureWizardPage() {
    this("You should configure plug-in development target platform before any eRCP project creation.");
  }

  public ConfigureWizardPage(String message) {
    super("eRCP configurations");
    setTitle("eRCP configuration");
    setMessage(message);
    setPageComplete(false);
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // GUI
  //
  ////////////////////////////////////////////////////////////////////////////
  public void createControl(Composite parent) {
    initializeDialogUnits(parent);
    // container
    Composite composite = new Composite(parent, SWT.NULL);
    composite.setFont(parent.getFont());
    composite.setLayout(new GridLayout());
    //
    new Label(composite, SWT.NONE).setText("1. Download eRCP distribution and extract it somewhere on disk [eRCP location].");
    // link to open preferences
    {
      Link openPreferencesLink = new Link(composite, SWT.NONE);
      openPreferencesLink.setText("2. <a>Click here to open target plarform configuration</a>");
      openPreferencesLink.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent e) {
          PreferencesUtil.createPreferenceDialogOn(
              getShell(),
              "org.eclipse.pde.ui.TargetPlatformPreferencePage",
              ArrayUtils.EMPTY_STRING_ARRAY,
              null).open();
          if (isConfigured_eRCP()) {
            setPageComplete(true);
            getWizard().getContainer().showPage(getNextPage());
          }
        }
      });
    }
    new Label(composite, SWT.NONE).setText("3. Use \"Browser...\" and select directory [eRCP location]\\eRCP\\win32.");
    //
    setControl(composite);
    Dialog.applyDialogFont(composite);
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Check
  //
  ////////////////////////////////////////////////////////////////////////////
  /**
   * @return <code>true</code> if workspace (Plug-in development target platform) in configuring for
   *         using eRCP.
   */
  static boolean isConfigured_eRCP() {
    // prepare list of plugins in target platform
    List<String> pluginIds = Lists.newArrayList();
    for (IPluginModelBase plugin : PluginRegistry.getExternalModels()) {
      pluginIds.add(plugin.getBundleDescription().getSymbolicName());
    }
    // check for known eRCP plugins
    return pluginIds.contains("org.eclipse.ercp.eworkbench");
  }
}
