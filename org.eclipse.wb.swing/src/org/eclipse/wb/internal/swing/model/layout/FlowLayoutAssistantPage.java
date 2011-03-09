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
package org.eclipse.wb.internal.swing.model.layout;

import org.eclipse.wb.core.editor.actions.assistant.AbstractAssistantPage;
import org.eclipse.wb.internal.core.utils.ui.GridDataFactory;
import org.eclipse.wb.internal.core.utils.ui.GridLayoutFactory;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import java.awt.FlowLayout;

/**
 * Layout assistant for {@link FlowLayout}.
 * 
 * @author lobas_av
 * @coverage swing.assistant
 */
public final class FlowLayoutAssistantPage extends AbstractAssistantPage {
  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  public FlowLayoutAssistantPage(Composite parent, Object selection) {
    super(parent, selection);
    GridLayoutFactory.create(this).columns(2);
    // orientation
    {
      Group orientationGroup =
          addChoiceProperty(this, "alignment", "Alignment", new Object[][]{
              new Object[]{"Left", FlowLayout.LEFT},
              new Object[]{"Center", FlowLayout.CENTER},
              new Object[]{"Right", FlowLayout.RIGHT},
              new Object[]{"Leading", FlowLayout.LEADING},
              new Object[]{"Trailing", FlowLayout.TRAILING}});
      GridDataFactory.create(orientationGroup).fillV();
    }
    //
    {
      Group gapGroup =
          addIntegerProperties(this, "Gaps", new String[][]{
              {"hgap", "Horizontal gap:"},
              {"vgap", "Vertical gap:"}});
      GridDataFactory.create(gapGroup).fillV();
    }
  }
}