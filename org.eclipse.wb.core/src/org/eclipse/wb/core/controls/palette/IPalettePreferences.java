/*******************************************************************************
 * Copyright (c) 2011, 2022 Google, Inc. and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Google, Inc. - initial API and implementation
 *    Daten- und Systemtechnik Aachen - Added getLayoutType method
 *******************************************************************************/
package org.eclipse.wb.core.controls.palette;

import org.eclipse.swt.graphics.Font;

/**
 * Provider for preferences of {@link PaletteComposite}.
 *
 * @author scheglov_ke
 * @coverage core.control.palette
 */
public interface IPalettePreferences {
  /**
   * @return the {@link Font} for {@link ICategory}.
   */
  Font getCategoryFont();

  /**
   * @return the {@link Font} for {@link IEntry}.
   */
  Font getEntryFont();

  /**
   * @return <code>true</code> if only icons should be displayed for {@link IEntry}'s.
   */
  boolean isOnlyIcons();

  /**
   * @return the minimal number of columns for {@link ICategory}.
   */
  int getMinColumns();

  /**
   *
   * @return the specified layout type. Layout type is specified in
   * @see IPaletteLayoutConstants
   */
  int getLayoutType();
}
