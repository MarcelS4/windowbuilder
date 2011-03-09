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

import org.eclipse.wb.internal.core.DesignerPlugin;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import java.lang.reflect.InvocationTargetException;

/**
 * {@link Wizard} that creates new eRCP project.
 * 
 * @author scheglov_ke
 * @coverage ercp.wizards.ui
 */
public final class NewProjectWizard extends Wizard implements INewWizard {
  private NewProjectCreationPage m_mainPage;

  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  public NewProjectWizard() {
    setWindowTitle("New eRCP Project");
    setNeedsProgressMonitor(true);
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Pages
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  public void addPages() {
    if (!ConfigureWizardPage.isConfigured_eRCP()) {
      addPage(new ConfigureWizardPage());
    }
    {
      m_mainPage = new NewProjectCreationPage("main");
      m_mainPage.setTitle("eRCP project");
      m_mainPage.setDescription("Create a new eRCP project.");
      addPage(m_mainPage);
    }
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Finish
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  public boolean performFinish() {
    try {
      String projectName = m_mainPage.getProjectName();
      NewProjectCreationOperation operation =
          new NewProjectCreationOperation(projectName, m_mainPage.shouldGenerateSampleProject());
      getContainer().run(false, true, operation);
      return true;
    } catch (InvocationTargetException e) {
      DesignerPlugin.log(e);
    } catch (InterruptedException e) {
    }
    return false;
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // IWorkbenchWizard
  //
  ////////////////////////////////////////////////////////////////////////////
  public void init(IWorkbench workbench, IStructuredSelection selection) {
  }
}
