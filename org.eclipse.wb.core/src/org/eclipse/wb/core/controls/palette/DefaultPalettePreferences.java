/*******************************************************************************
 * Copyright (c) 2011, 2022 Google, Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Google, Inc. - initial API and implementation
 *    Daten- und Systemtechnik Aachen - layout type added
 *******************************************************************************/
package org.eclipse.wb.core.controls.palette;

import org.eclipse.swt.graphics.Font;

/**
 * The default implementation of {@link IPalettePreferences}.
 *
 * @author scheglov_ke
 * @coverage core.control.palette
 */
public final class DefaultPalettePreferences implements IPalettePreferences {
  @Override
  public Font getCategoryFont() {
    return null;
  }

  @Override
  public Font getEntryFont() {
    return null;
  }

  @Override
  public boolean isOnlyIcons() {
    return false;
  }

  @Override
  public int getMinColumns() {
    return 1;
  }

  /**
   * @return the default layout type
   */
  @Override
  public int getLayoutType() {
    return IPaletteLayoutConstants.COLUMN_ICONS_TYPE;
  }
}
