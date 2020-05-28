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
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.intermine.metadata.Model;
import org.apache.commons.lang.StringUtils;
import org.intermine.dataconversion.ItemWriter;
import org.intermine.objectstore.ObjectStoreException;
import org.intermine.util.FormattedTextParser;
import org.intermine.metadata.Model;
import org.intermine.xml.full.Item;

/**
 * 
 * @author
 */
public class AllianceGenesConverter extends BioFileConverter
{
    // 
    private static final String DATASET_TITLE = "Add DataSet.title here";
    private static final String DATA_SOURCE_NAME = "Add DataSource.name here";
    private String licence;
    private Map<String, Item> genes = new HashMap();

    private static final String TAXON_ID = "4932";
    private Item organism;

    /**
     * Construct a new AllianceGenesConverter.
     * @param database the database to read from
     * @param model the Model used by the object store we will write to with the ItemWriter
     * @param writer an ItemWriter used to handle Items created
     */
    public AllianceGenesConverter(ItemWriter writer, Model model) {
        super(writer, model, DATA_SOURCE_NAME, DATASET_TITLE);
    }


    /**
     * {@inheritDoc}
     */
    public void process(Reader reader) throws Exception {
        Iterator<?> lineIter = FormattedTextParser.parseTabDelimitedReader(reader);

        while (lineIter.hasNext()) {

            String[] line = (String[]) lineIter.next();
            String primaryIdentifier = line[0].trim();
            String name = line[1].trim();
            String description = line[2].trim();
            System.out.println("Processing line.." + primaryIdentifier);
            Item gene = createItem("Gene");
            gene.setAttribute("primaryIdentifier", primaryIdentifier);
            if(StringUtils.isNotEmpty(name)) { gene.setAttribute("symbol", name); }
            if(StringUtils.isNotEmpty(description)) { gene.setAttribute("briefDescription", description);}
            //gene.setReference("organism", getOrganism(taxonId));
            store(gene);
        }
    }


}
