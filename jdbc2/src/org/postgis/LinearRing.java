/*
 * LinearRing.java
 * 
 * PostGIS extension for PostgreSQL JDBC driver - geometry model
 * 
 * (C) 2004 Paul Ramsey, pramsey@refractions.net
 * 
 * (C) 2005 Markus Schaber, schabios@logi-track.com
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 2.1 of the License.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA or visit the web at
 * http://www.gnu.org.
 * 
 * $Id$
 */

package org.postgis;

import org.postgresql.util.PGtokenizer;

import java.sql.SQLException;

/**
 * This represents the LinearRing GIS datatype. This type is used to construct
 * the polygon types, but is not stored or retrieved directly from the database.
 */
public class LinearRing extends PointComposedGeom {

    public LinearRing(Point[] points) {
        super(LINEARRING, points);
    }

    /**
     * This is called to construct a LinearRing from the PostGIS string
     * representation of a ring.
     * 
     * @param value Definition of this ring in the PostGIS string format.
     */
    public LinearRing(String value) throws SQLException {
        super(LINEARRING);
        PGtokenizer t = new PGtokenizer(PGtokenizer.removePara(value.trim()), ',');
        int npoints = t.getSize();
        Point[] points = new Point[npoints];
        for (int p = 0; p < npoints; p++) {
            points[p] = new Point(t.getToken(p));
        }
        dimension = points[0].dimension;
        this.subgeoms = points;
    }
}