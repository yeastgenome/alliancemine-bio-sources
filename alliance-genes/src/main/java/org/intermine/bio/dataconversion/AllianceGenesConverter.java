package org.intermine.bio.dataconversion;

/*
 * Copyright (C) 2002-2016 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import org.intermine.dataconversion.ItemWriter;
import org.intermine.metadata.Model;
import org.intermine.sql.Database;
import org.intermine.xml.full.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author
 */
public class AllianceGenesConverter extends BioDBConverter
{
    // 
    private static final String DATASET_TITLE = "Add DataSet.title here";
    private static final String DATA_SOURCE_NAME = "Add DataSource.name here";
    private String licence;


    /**
     * Construct a new AllianceGenesConverter.
     * @param database the database to read from
     * @param model the Model used by the object store we will write to with the ItemWriter
     * @param writer an ItemWriter used to handle Items created
     */
    public AllianceGenesConverter(Database database, Model model, ItemWriter writer) {
        super(database, model, writer, DATA_SOURCE_NAME, DATASET_TITLE);
    }


    /**
     * {@inheritDoc}
     */
    public void process() throws Exception {
        // a database has been initialised from properties starting with db.alliance-genes

        Connection connection = getDatabase().getConnection();

        // process data with direct SQL queries on the source database, for example:
        
        // Statement stmt = connection.createStatement();
        // String query = "select column from table;";
        // ResultSet res = stmt.executeQuery(query);
        // while (res.next()) {
        // }   
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDataSetTitle(String taxonId) {
        return DATASET_TITLE;
    }

    /**
     * Set the data licence for these data.
     *
     * @param licence should be URI to data licence.
     */
    public void setLicence(String licence) {
        this.licence = licence;
    }

    /**
     * Get the data licence for these data.
     *
     * @return URI to data licence.
     */
    public String getLicence() {
        return licence;
    }

}
