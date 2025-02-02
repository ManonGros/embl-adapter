SELECT ed.accession,
       ed.sample_accession,
       ed.location,
       ed.country,
       ed.identified_by,
       ed.collected_by,
       ed.collection_date,
       ed.specimen_voucher,
       ed.sequence_md5,
       ed.scientific_name,
       ed.tax_id,
       ed.altitude,
       ed.sex,
       ed.description,
       et.kingdom,
       et.phylum,
       et.class,
       et."order",
       et.family,
       et.genus
FROM (
         SELECT row_number()
                over (PARTITION BY tax_id, scientific_name, collection_date, location, country, collected_by, identified_by, sample_accession ORDER BY tax_id) as row_num,
                accession,
                sample_accession,
                location,
                country,
                identified_by,
                collected_by,
                collection_date,
                specimen_voucher,
                sequence_md5,
                scientific_name,
                tax_id,
                altitude,
                sex,
                description
         FROM embl_data1
         WHERE scientific_name != 'Homo sapiens') as ed
         LEFT JOIN ena_taxonomy et ON ed.tax_id = et.taxon_id
WHERE ed.row_num < 50
