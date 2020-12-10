BEGIN TRANSACTION;

 INSERT INTO crops 
 (
     name,
     yield_lbs_per_square_foot,
     crops_per_square_foot,
     seed_cost,
     description
 ) 
 VALUES 
 (
     'Basil',
     0,
     3,
     0.00,
     ''
 ),
 (
     'Beets',
     0,
     9,
     0.00,
     ''
 ),
 (
     'Broccoli',
     0,
     1,
     0.00,
     ''
 ),
 (
     'Brussel Sprout',
     0,
     1,
     0.00,
     ''
 ),
 (
     'Cabbage',
     0,
     1,
     0.00,
     ''
 ),
 (
     'Carrots',
     0,
     12,
     0.00,
     ''
 ),
 (
     'Cauliflower',
     0,
     1,
     0.00,
     ''
 ),
 (
     'Celery',
     0,
     4,
     0.00,
     ''
 ),
 (
     'Corn',
     0,
     4,
     0.00,
     ''
 ),
 (
     'Cucumbers',
     0,
     2,
     0.00,
     ''
 ),
 (
     'Green Onions',
     0,
     16,
     0.00,
     ''
 ),
 (
     'Lettuce',
     0,
     2,
     0.00,
     ''
 ),
 (
     'Onions (bunching)',
     0,
     9,
     0.00,
     ''
 ),
 (
     'Onions (large)',
     0,
     3,
     0.00,
     ''
 ),
 (
     'Peas',
     0,
     6,
     0.00,
     ''
 ),
 (
     'Peppers',
     0,
     1,
     0.00,
     ''
 ),
 (
     'Potatoes',
     0,
     4,
     0.00,
     ''
 ),
 (
     'Radishes',
     0,
     14,
     0.00,
     ''
 ),
 (
     'Spinach',
     0,
     9,
     0.00,
     ''
 ),
 (
     'Strawberry',
     0,
     2,
     0.00,
     ''
 ),
 (
     'Tomatoes',
     0,
     1,
     0.00,
     ''
 ),
 (
     'Turnips',
     0,
     9,
     0.00,
     ''
 ),
 (
     'Yams',
     0,
     4,
     0.00,
     ''
 );
 
 -- FAKE TEMPORARY DATA FOR TESTING
 -- Username: test@test
 -- Password: test
 
 INSERT INTO users (user_id,username,password_hash,role) VALUES (101,'test@test','$2a$10$77wEyLK.8WdPMfsvD60iYetDMsXq5FjFBhoqbz5RaDDfhm9ZeIAKa','ROLE_USER');
 
 INSERT INTO plot 
 (
     plot_id,
     width,
     length,
     active
 ) 
 VALUES 
 (
     101,
     4,
     4,
     't'
 );
 
 INSERT INTO users_plot
 (
     user_id,
     plot_id
 ) 
 VALUES 
 (
     101,
     101
 );
 
 INSERT INTO plot_coords
 (
     coords_id,
     crop_id,
     plot_id,
     x,
     y
 ) 
 VALUES 
 (
     101,
     6,
     101,
     0,
     0
 ),
 (
     102,
     6,
     101,
     0,
     1
 ),
 (
     103,
     6,
     101,
     0,
     2
 ),
 (
     104,
     6,
     101,
     0,
     3
 ),
 (
     105,
     10,
     101,
     1,
     0
 ),
 (
     106,
     10,
     101,
     1,
     1
 ),
 (
     107,
     10,
     101,
     1,
     2
 ),
 (
     108,
     10,
     101,
     1,
     3
 ),
 (
     109,
     17,
     101,
     2,
     0
 ),
 (
     110,
     17,
     101,
     2,
     1
 ),
 (
     111,
     17,
     101,
     2,
     2
 ),
 (
     112,
     17,
     101,
     2,
     3
 ),
 (
     113,
     21,
     101,
     3,
     0
 ),
 (
     114,
     21,
     101,
     3,
     1
 ),
 (
     115,
     21,
     101,
     3,
     2
 ),
 (
     116,
     21,
     101,
     3,
     3
 );
 
 INSERT INTO notes
 (
     note_id,
     plot_id,
     note
 ) 
 VALUES 
 (
     101,
     101,
     'This is the best plot there ever was.'
 );

COMMIT TRANSACTION;