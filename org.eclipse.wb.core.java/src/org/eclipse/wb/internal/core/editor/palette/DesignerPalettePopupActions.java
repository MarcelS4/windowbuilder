/*******************************************************************************
 * Copyright (c) 2011 Google, Inc and Others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Google, Inc. - initial API and implementation
 *    Daten- und Systemtechnik Aachen - Addition of Icons display types
 *******************************************************************************/
package org.eclipse.wb.internal.core.editor.palette;

import org.eclipse.wb.core.controls.palette.IPalette;
import org.eclipse.wb.core.controls.palette.IPaletteLayoutConstants;
import org.eclipse.wb.core.editor.palette.model.CategoryInfo;
import org.eclipse.wb.core.editor.palette.model.EntryInfo;
import org.eclipse.wb.internal.core.DesignerPlugin;
import org.eclipse.wb.internal.core.editor.Messages;
import org.eclipse.wb.internal.core.editor.palette.DesignerPalette.DesignerPaletteOperations;
import org.eclipse.wb.internal.core.utils.execution.ExecutionUtils;
import org.eclipse.wb.internal.core.utils.execution.RunnableEx;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * Helper class adding popup actions into palette.
 *
 * @author mitin_aa
 * @coverage core.editor.palette.ui
 */
final class DesignerPalettePopupActions {
  // image constants
  private static final ImageDescriptor ID_ADD_CATEGORY = getImageDescription("add_category.gif");
  private static final ImageDescriptor ID_ADD_COMPONENT = getImageDescription("add_component.gif");
  private static final ImageDescriptor IMPORT_JAR_IMAGE = getImageDescription("import_jar.png");
  private static final ImageDescriptor ID_REMOVE = getImageDescription("remove.gif");
  private static final ImageDescriptor ID_MANAGER = getImageDescription("manager.gif");
  private static final ImageDescriptor ID_SETTINGS = getImageDescription("settings.png");
  private static final ImageDescriptor ID_IMPORT = getImageDescription("import.png");
  private static final ImageDescriptor ID_EXPORT = getImageDescription("export.png");
  private static final ImageDescriptor ID_SELECTED = getImageDescription("selected.gif");
  // field
  private final DesignerPaletteOperations m_operations;
  private final PluginPalettePreferences m_preferences =
      new PluginPalettePreferences(DesignerPlugin.getPreferences());

  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  public DesignerPalettePopupActions(DesignerPaletteOperations operations) {
    m_operations = operations;
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Popup
  //
  ////////////////////////////////////////////////////////////////////////////
  /**
   * @return the {@link ImageDescriptor} for palette image.
   */
  private static ImageDescriptor getImageDescription(String path) {
    return DesignerPlugin.getImageDescriptor("palette/" + path);
  }

  /**
   * @see IPalette#addPopupActions(IMenuManager, Object, int)
   */
  void addPopupActions(IMenuManager menuManager, Object target, int iconsType) {
    if (m_operations.canEditPalette()) {
      addPopupActions_edit(menuManager, target, iconsType);
    }
    //
    menuManager.add(new Separator());
    // settings
    {
      Action settingsAction =
          new Action(Messages.DesignerPalettePopupActions_settingsAction, ID_SETTINGS) {
            @Override
            public void run() {
              m_operations.editPreferences();
            }
          };
      menuManager.add(settingsAction);
    }
  }

  private void addPopupActions_edit(IMenuManager menuManager, Object target, int type) {
    // add layout action
    {
      IMenuManager layoutMenuManager = new MenuManager("Layout");
      menuManager.add(layoutMenuManager);
      // single
      {
        ImageDescriptor image = null;
        if (type == IPaletteLayoutConstants.COLUMN_ICONS_TYPE) {
          image = ID_SELECTED;
        }
        Action columnAction = new Action("Columns", image) {
          @Override
          public void run() {
            m_operations.setIconsType(IPaletteLayoutConstants.COLUMN_ICONS_TYPE);
          }
        };
        layoutMenuManager.add(columnAction);
        image = null;
        if (type == IPaletteLayoutConstants.LIST_ICONS_TYPE) {
          image = ID_SELECTED;
        }
        Action listAction = new Action("List", image) {
          @Override
          public void run() {
            m_operations.setIconsType(IPaletteLayoutConstants.LIST_ICONS_TYPE);
          }
        };
        layoutMenuManager.add(listAction);
        image = null;
        if (type == IPaletteLayoutConstants.ONLY_ICONS_TYPE) {
          image = ID_SELECTED;
        }
        Action onlyIconAction = new Action("Icons Only", image) {
          @Override
          public void run() {
            m_operations.setIconsType(IPaletteLayoutConstants.ONLY_ICONS_TYPE);
          }
        };
        layoutMenuManager.add(onlyIconAction);
        image = null;
        if (type == IPaletteLayoutConstants.DETAIL_ICONS_TYPE) {
          image = ID_SELECTED;
        }
        Action detailAction = new Action("Detail", image) {
          @Override
          public void run() {
            m_operations.setIconsType(IPaletteLayoutConstants.DETAIL_ICONS_TYPE);
          }
        };
        layoutMenuManager.add(detailAction);
      }
    }
    // separator
    menuManager.add(new Separator());
  }

  /**
   * @return the {@link IAction} for adding single factory method.
   */
  private IAction popup_createAction_addFactory(final CategoryInfo category,
      final boolean forStatic) {
    return new Action(Messages.DesignerPalettePopupActions_factorySingleAction) {
      @Override
      public void run() {
        m_operations.addFactory(category, forStatic);
      }
    };
  }

  /**
   * @return the {@link IAction} for adding multiple factory methods.
   */
  private IAction popup_createAction_addFactories(final CategoryInfo category,
      final boolean forStatic) {
    return new Action(Messages.DesignerPalettePopupActions_factoryMultipleAction) {
      @Override
      public void run() {
        m_operations.addFactories(category, forStatic);
      }
    };
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Import/export
  //
  ////////////////////////////////////////////////////////////////////////////
  private void addImportExport(IMenuManager menuManager) {
    Action importAction = new Action(Messages.DesignerPalettePopupActions_importAction, ID_IMPORT) {
      @Override
      public void run() {
        importPalette();
      }
    };
    Action exportAction = new Action(Messages.DesignerPalettePopupActions_exportAction, ID_EXPORT) {
      @Override
      public void run() {
        exportPalette();
      }
    };
    menuManager.add(importAction);
    menuManager.add(exportAction);
  }

  private void exportPalette() {
    final String path = getImportExportPath(SWT.SAVE);
    if (path != null) {
      ExecutionUtils.runLog(new RunnableEx() {
        public void run() throws Exception {
          m_operations.exportPalette(path);
        }
      });
    }
  }

  private void importPalette() {
    final String path = getImportExportPath(SWT.OPEN);
    if (path != null) {
      ExecutionUtils.runLog(new RunnableEx() {
        public void run() throws Exception {
          m_operations.importPalette(path);
        }
      });
    }
  }

  private String getImportExportPath(int style) {
    FileDialog fileDialog = new FileDialog(getShell(), style);
    fileDialog.setFilterExtensions(new String[]{"*.xml"});
    fileDialog.setFilterNames(new String[]{Messages.DesignerPalettePopupActions_paletteFilterName});
    fileDialog.setFileName(m_operations.getToolkitId() + ".xml");
    return fileDialog.open();
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Misc
  //
  ////////////////////////////////////////////////////////////////////////////
  private Shell getShell() {
    return m_operations.getShell();
  }

  private CategoryInfo getCategory(Object target) {
    return m_operations.getCategory(target);
  }

  private EntryInfo getEntry(Object target) {
    return m_operations.getEntry(target);
  }
}
