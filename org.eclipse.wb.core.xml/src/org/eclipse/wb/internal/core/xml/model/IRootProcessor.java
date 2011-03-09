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
package org.eclipse.wb.internal.core.xml.model;

/**
 * Processor for root {@link XmlObjectInfo}.
 * 
 * @author scheglov_ke
 * @coverage XML.model
 */
public interface IRootProcessor {
  /**
   * Last step after building {@link XmlObjectInfo} tree.
   */
  void process(XmlObjectInfo root) throws Exception;
}
