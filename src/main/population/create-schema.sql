
    create table `administrator` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `alferez_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(255),
        `moment` datetime(6),
        `type` varchar(255),
        `url` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `announcement` (
       `id` integer not null,
        `version` integer not null,
        `moment` datetime(6),
        `more_info` varchar(255),
        `text` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `anonymous` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `application` (
       `id` integer not null,
        `version` integer not null,
        `last_update` datetime(6),
        `moment` datetime(6),
        `qualifications` varchar(255),
        `reference` varchar(255),
        `reject_reason` varchar(255),
        `skills` varchar(255),
        `statement` varchar(255),
        `status` varchar(255),
        `job_id` integer not null,
        `worker_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `auditor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `firm` varchar(255),
        `responsability_stat` varchar(255),
        `status` bit not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `auditrecord` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `moment` datetime(6),
        `reference` varchar(255),
        `status` bit not null,
        `title` varchar(255),
        `auditor_id` integer not null,
        `job_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `authenticated` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `cardenas_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(255),
        `book` varchar(255),
        `genre` varchar(255),
        `isbn` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `challenge` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `description` varchar(255),
        `goal` varchar(255),
        `reward` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `chellik_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `apellido` varchar(255),
        `fecha` datetime(6),
        `nombre` varchar(255),
        `role` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `commercial_banner` (
       `id` integer not null,
        `version` integer not null,
        `picture` varchar(255),
        `slogan` varchar(255),
        `targeturl` varchar(255),
        `cc_expiration_date` varchar(255),
        `cc_validation_number` varchar(255),
        `credit_card_number` varchar(255),
        `sponsor_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `company_record` (
       `id` integer not null,
        `version` integer not null,
        `ceoname` varchar(255),
        `activities_description` varchar(255),
        `company_name` varchar(255),
        `contact_email` varchar(255),
        `contact_phone` varchar(255),
        `incorporated` bit,
        `sector` varchar(255),
        `star_score` double precision,
        `website` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `consumer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `customisation_parameter` (
       `id` integer not null,
        `version` integer not null,
        `spam_threshold` double precision,
        `spam_words_en` varchar(255),
        `spam_words_es` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `descriptor` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `duty` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(255),
        `percentage` double precision,
        `title` varchar(255),
        `descriptor_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `employer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `escobar_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(255),
        `birthdate` datetime(6),
        `level` varchar(255),
        `moment` datetime(6),
        `text` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `investor_record` (
       `id` integer not null,
        `version` integer not null,
        `investing_statement` varchar(255),
        `investor_name` varchar(255),
        `sector` varchar(255),
        `stars` double precision,
        primary key (`id`)
    ) engine=InnoDB;

    create table `job` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `final_mode` bit not null,
        `more_info` varchar(255),
        `reference` varchar(255),
        `salary_amount` double precision,
        `salary_currency` varchar(255),
        `title` varchar(255),
        `descriptor_id` integer not null,
        `employer_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `message` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `moment` datetime(6),
        `tags` varchar(255),
        `title` varchar(255),
        `thread_id` integer not null,
        `user_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `non_commercial_banner` (
       `id` integer not null,
        `version` integer not null,
        `picture` varchar(255),
        `slogan` varchar(255),
        `targeturl` varchar(255),
        `jingle` varchar(255),
        `sponsor_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `offer` (
       `id` integer not null,
        `version` integer not null,
        `creation` datetime(6),
        `deadline` datetime(6),
        `reward_max_amount` double precision,
        `reward_max_currency` varchar(255),
        `reward_min_amount` double precision,
        `reward_min_currency` varchar(255),
        `text` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `provider` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `request_auditor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `firm` varchar(255),
        `responsability_stat` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `requests` (
       `id` integer not null,
        `version` integer not null,
        `creation` datetime(6),
        `deadline` datetime(6),
        `reward_amount` double precision,
        `reward_currency` varchar(255),
        `text` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `rosado_bulletin` (
       `id` integer not null,
        `version` integer not null,
        `image` varchar(255),
        `name` varchar(255),
        `video` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `shout` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(255),
        `moment` datetime(6),
        `text` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `sponsor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `cc_expiration_date` varchar(255),
        `cc_validation_number` varchar(255),
        `company` varchar(255),
        `credit_card_number` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `thread` (
       `id` integer not null,
        `version` integer not null,
        `moment` datetime(6),
        `title` varchar(255),
        `creator_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `thread_authenticated` (
       `thread_id` integer not null,
        `users_id` integer not null
    ) engine=InnoDB;

    create table `user_account` (
       `id` integer not null,
        `version` integer not null,
        `enabled` bit not null,
        `identity_email` varchar(255),
        `identity_name` varchar(255),
        `identity_surname` varchar(255),
        `password` varchar(255),
        `username` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `worker` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `qualifications_record` varchar(255),
        `skills_record` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `hibernate_sequence` (
       `next_val` bigint
    ) engine=InnoDB;

    insert into `hibernate_sequence` values ( 1 );

    alter table `application` 
       add constraint UK_ct7r18vvxl5g4c4k7aefpa4do unique (`reference`);

    alter table `auditrecord` 
       add constraint UK_q0jl5notxl79ibm8ugpnyshex unique (`reference`);
create index IDXnr284tes3x8hnd3h716tmb3fr on `challenge` (`deadline`);
create index IDXmgvhy7dlsdj1h0c8yb46i6by1 on `challenge` (`title`, `deadline`);
create index IDXh7bve39okm3gjs4blcchj12j2 on `company_record` (`star_score`);
create index IDXk2t3uthe649ao1jllcuks0gv4 on `investor_record` (`stars`);

    alter table `job` 
       add constraint UK_qpodqtu8nvqkof3olnqnqcv2l unique (`descriptor_id`);

    alter table `job` 
       add constraint UK_7jmfdvs0b0jx7i33qxgv22h7b unique (`reference`);
create index IDX6vvdfk8bhs718y3psyx1p1p9d on `offer` (`id`, `deadline`);
create index IDX2jcikaiqhqcdfy62sn4d3ph58 on `offer` (`ticker`, `deadline`);
create index IDXq2o9psuqfuqmq59f0sq57x9uf on `offer` (`deadline`);

    alter table `offer` 
       add constraint UK_iex7e8fs0fh89yxpcnm1orjkm unique (`ticker`);
create index IDX289opkba5wrr0u79x133vuui5 on `requests` (`id`, `deadline`);
create index IDXolwqknfiuvtep9yrmfnwlnrs4 on `requests` (`ticker`, `deadline`);
create index IDXmly5kwrpgadjkxv5t5dgw36hr on `requests` (`deadline`);

    alter table `requests` 
       add constraint UK_5v1h0kdr8vcps4i9e55k5gnc8 unique (`ticker`);

    alter table `user_account` 
       add constraint UK_castjbvpeeus0r8lbpehiu0e4 unique (`username`);

    alter table `administrator` 
       add constraint FK_2a5vcjo3stlfcwadosjfq49l1 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `anonymous` 
       add constraint FK_6lnbc6fo3om54vugoh8icg78m 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `application` 
       add constraint `FKoa6p4s2oyy7tf80xwc4r04vh6` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `application` 
       add constraint `FKmbjdoxi3o93agxosoate4sxbt` 
       foreign key (`worker_id`) 
       references `worker` (`id`);

    alter table `auditor` 
       add constraint FK_clqcq9lyspxdxcp6o4f3vkelj 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `auditrecord` 
       add constraint `FKditgyx355sc4ye86w7tj22cq6` 
       foreign key (`auditor_id`) 
       references `auditor` (`id`);

    alter table `auditrecord` 
       add constraint `FKa5p4w0gnuwmtb07juvrg8ptn6` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `authenticated` 
       add constraint FK_h52w0f3wjoi68b63wv9vwon57 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `commercial_banner` 
       add constraint `FKd0k52g7lcacefcp62kb4p9aor` 
       foreign key (`sponsor_id`) 
       references `sponsor` (`id`);

    alter table `consumer` 
       add constraint FK_6cyha9f1wpj0dpbxrrjddrqed 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `duty` 
       add constraint `FK3cc3garl37bl7gswreqwr7pj4` 
       foreign key (`descriptor_id`) 
       references `descriptor` (`id`);

    alter table `employer` 
       add constraint FK_na4dfobmeuxkwf6p75abmb2tr 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `job` 
       add constraint `FKfqwyynnbcsq0htxho3vchpd2u` 
       foreign key (`descriptor_id`) 
       references `descriptor` (`id`);

    alter table `job` 
       add constraint `FK3rxjf8uh6fh2u990pe8i2at0e` 
       foreign key (`employer_id`) 
       references `employer` (`id`);

    alter table `message` 
       add constraint `FK28hjkn063wrsjuiyyf8sm3s2v` 
       foreign key (`thread_id`) 
       references `thread` (`id`);

    alter table `message` 
       add constraint `FKik4epe9dp5q6uenarfyia7xin` 
       foreign key (`user_id`) 
       references `authenticated` (`id`);

    alter table `non_commercial_banner` 
       add constraint `FKpcpr0xb5k7s4rxv5pulstt5v9` 
       foreign key (`sponsor_id`) 
       references `sponsor` (`id`);

    alter table `provider` 
       add constraint FK_b1gwnjqm6ggy9yuiqm0o4rlmd 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `request_auditor` 
       add constraint FK_ie2ocrruj5nai12m6h4a0fmtw 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `sponsor` 
       add constraint FK_20xk0ev32hlg96kqynl6laie2 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `thread` 
       add constraint `FKi6qpt5u0ti53aogdftkunyp6r` 
       foreign key (`creator_id`) 
       references `authenticated` (`id`);

    alter table `thread_authenticated` 
       add constraint `FKkuamwlt147dsxim98bfhh4dsr` 
       foreign key (`users_id`) 
       references `authenticated` (`id`);

    alter table `thread_authenticated` 
       add constraint `FKjsja3s5mr66x5nxm9dd8kut3r` 
       foreign key (`thread_id`) 
       references `thread` (`id`);

    alter table `worker` 
       add constraint FK_l5q1f33vs2drypmbdhpdgwfv3 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);
