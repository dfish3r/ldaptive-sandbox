/*
  $Id$

  Copyright (C) 2003-2014 Virginia Tech.
  All rights reserved.

  SEE LICENSE FOR MORE INFORMATION

  Author:  Middleware Services
  Email:   middleware@vt.edu
  Version: $Revision$
  Updated: $Date$
*/
package org.ldaptive.beans;

/**
 * Interface for mutating the DN value on an arbitrary object.
 *
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public interface DnValueMutator
{

  /**
   * Returns the DN value for the supplied object.
   *
   * @param  object  to return the DN of
   *
   * @return  DN value
   */
  String getValue(final Object object);


  /**
   * Set the DN value for the supplied object.
   *
   * @param  object  to set the DN on
   * @param  value  of the DN
   */
  void setValue(final Object object, final String value);
}
