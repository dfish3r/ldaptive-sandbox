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

import org.ldaptive.LdapEntry;

/**
 * Interface for ldap entry mappers.
 *
 * @param  <T>  type of object to map
 *
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public interface LdapEntryMapper<T>
{

  /**
   * Returns the LDAP DN for the supplied object.
   *
   * @param  object  to retrieve the DN from
   *
   * @return  LDAP DN
   */
  String getDn(T object);


  /**
   * Injects data from the supplied source object into the supplied ldap entry.
   *
   * @param  source  to read from
   * @param  dest  to write to
   */
  void map(T source, LdapEntry dest);


  /**
   * Injects data from the supplied ldap entry into the supplied destination
   * object.
   *
   * @param  source  to read from
   * @param  dest  to write to
   */
  void map(LdapEntry source, T dest);
}
