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
package org.ldaptive.beans.reflect;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.ldaptive.LdapUtils;
import org.ldaptive.SortBehavior;
import org.ldaptive.beans.Attribute;
import org.ldaptive.beans.Entry;

/**
 * Class for testing bean annotations.
 *
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
@Entry(
  dn = "customDn",
  attributes = {
    @Attribute(name = "customname1", values = "customvalue1"),
    @Attribute(name = "customname2", values = {"customvalue1", "customvalue2"}),
    @Attribute(name = "type1", property = "type1"),
    @Attribute(name = "type2", property = "type2"),
    @Attribute(name = "stringthree", property = "type3"),
    @Attribute(name = "typeCol1", property = "typeCol1"),
    @Attribute(name = "typeCol2", property = "typeCol2"),
    @Attribute(name = "typeSet1", property = "typeSet1"),
    @Attribute(name = "typeSet2", property = "typeSet2"),
    @Attribute(
      name = "typeList1",
      property = "typeList1",
      sortBehavior = SortBehavior.ORDERED),
    @Attribute(
      name = "typeList2",
      property = "typeList2",
      sortBehavior = SortBehavior.ORDERED)
    }
)
public class CharCustomObject implements CustomObject
{

  /** hash code seed. */
  private static final int HASH_CODE_SEED = 31;

  // CheckStyle:JavadocVariable OFF
  private String customDn;
  private char[] type1;
  private char[] type2;
  private char[] type3;
  private Collection<char[]> typeCol1;
  private Collection<char[]> typeCol2;
  private Set<char[]> typeSet1;
  private Set<char[]> typeSet2;
  private List<char[]> typeList1;
  private List<char[]> typeList2;
  // CheckStyle:JavadocVariable ON


  // CheckStyle:JavadocMethod OFF
  // CheckStyle:LeftCurly OFF
  public CharCustomObject() {}
  public CharCustomObject(final String s) { setCustomDn(s); }


  public String getCustomDn() { return customDn; }
  public void setCustomDn(final String s) { customDn = s; }
  public char[] getType1() { return type1; }
  public void setType1(final char[] t) { type1 = t; }
  public void writeType2(final char[] t) { type2 = t; }
  public char[] getType3() { return type3; }
  public void setType3(final char[] t) { type3 = t; }
  public Collection<char[]> getTypeCol1() { return typeCol1; }
  public void setTypeCol1(final Collection<char[]> c) { typeCol1 = c; }
  public void writeTypeCol2(final Collection<char[]> c) { typeCol2 = c; }
  public Set<char[]> getTypeSet1() { return typeSet1; }
  public void setTypeSet1(final Set<char[]> s) { typeSet1 = s; }
  public void writeTypeSet2(final Set<char[]> s) { typeSet2 = s; }
  public List<char[]> getTypeList1() { return typeList1; }
  public void setTypeList1(final List<char[]> l) { typeList1 = l; }
  public void writeTypeList2(final List<char[]> l) { typeList2 = l; }
  // CheckStyle:LeftCurly ON
  // CheckStyle:JavadocMethod ON


  /** {@inheritDoc} */
  @Override
  public boolean equals(final Object o)
  {
    return LdapUtils.areEqual(this, o);
  }


  /** {@inheritDoc} */
  @Override
  public int hashCode()
  {
    return
      LdapUtils.computeHashCode(
        HASH_CODE_SEED,
        getCustomDn(),
        type1,
        type2,
        type3,
        typeCol1,
        typeCol2,
        typeSet1,
        typeSet2,
        typeList1,
        typeList2);
  }


  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    return String.format(
      "[%s@%d::" +
      "customDn=%s, " +
      "type1=%s, type2=%s, type3=%s, " +
      "typeCol1=%s, typeCol2=%s, " +
      "typeSet1=%s, typeSet2=%s, " +
      "typeList1=%s, typeList2=%s]",
      getClass().getSimpleName(),
      hashCode(),
      getCustomDn(),
      Arrays.toString(type1),
      Arrays.toString(type2),
      Arrays.toString(type3),
      toString(typeCol1),
      toString(typeCol2),
      toString(typeSet1),
      toString(typeSet2),
      toString(typeList1),
      toString(typeList2));
  }


  /**
   * Returns a string representation of the supplied collection.
   *
   * @param  c  collection to represent as a string
   *
   * @return  collection as a string
   */
  private String toString(final Collection<char[]> c)
  {
    String s = null;
    if (c != null) {
      s = "";
      for (char[] t : c) {
        s += Arrays.toString((char[]) t);
      }
    }
    return s;
  }
}
