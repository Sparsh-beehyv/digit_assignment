CREATE TABLE IF NOT EXISTS eg_adv_register(
    id character varying(64),
    firstname character varying(64),
    middleName character varying(64),
    lastName character varying(64),
    applicationId character varying(64),
    tenantId character varying(64),
    state character varying(64),
    phone char(10),
    district character varying(64),
    city character varying(64),
    createdBy character varying(64),
    lastModifiedBy character varying(64),
    createdTime bigint,
    lastModifiedTime bigint,
    CONSTRAINT eg_adv_registration PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS eg_adv_bar_detail(
    id character varying(64),
    registrationId character varying(64),
    barRegistrationNum character varying(128),
    barRegistrationDocUrl text,
    designation character varying(64),
    createdBy character varying(64),
    lastModifiedBy character varying(64),
    createdTime bigint,
    lastModifiedTime bigint,
    CONSTRAINT eg_adv_details PRIMARY KEY (id),
    CONSTRAINT eg_adv_app UNIQUE (registrationId),
    CONSTRAINT adv_detail_fk FOREIGN KEY (registrationId) references eg_adv_register (id)
    ON DELETE CASCADE ON UPDATE CASCADE
);