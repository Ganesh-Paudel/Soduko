This is a Soduko game that i made in java 

It uses Swing for the gui and simple logic

The game looks like this:
![image](https://github.com/user-attachments/assets/acfd7089-fc38-44f0-b1e6-16f04f32c1db)


The Declarations:
  1. rows: for rows in the grid
  2. cols: for columns in the grid

  3. Class tile which extends buttons:
    a. this is so that i can have the coord and the value the tile holds which makes it easier to retrieve the value
    b. this also has setvalue and getvalue methods which do as the name says: sets and gets value for the tile

  4. Frame for the whole window,
  5. titlePanel and titleLabel which just shows the top bar and the text soduko
  6. middlePanel which is the mainboard
  7. Arraylists:
     a. wrongCols, wrongrows, wrongGrids: These are used for putting the tiles which contains the same numbers tiles in cols, rows, grid (3x3)
  8. Hashset:
     a. it's for filling the tiles with the numbers
     b. Random for generating the random tiles and putting random values

  9. Then in the constructor, i have made the GUI, then added action listener and keylistener which will be used to store the number pressed by the user
  10. Also, it initializes all the tiles and, calls some functions , conditions, fills the board with the numbers

  11. That's for the constructor and we have some methods below
  12. resetBoardBackground():
      a. it resets the background of the tiles to default white every time
  13. highlightWrongTiles():
      a. highlights the wrong tiles if in same cols, same rows, same grid

  14. checkCols, checkRows, checkGrid:
      a. checks the duplicate numbers in rows, cols, grid
  15. generatePrefilled numbers:
      a. 40 tiles are filled
      b. and calls CanPlaceNumbers functions which checks if we can place it in the tile or not checking the rows and cols values
      c. also it is disabled so that the user can't change it



![image](https://github.com/user-attachments/assets/e44cefa0-174d-4213-a2bf-893ecdb53da1)


  
  
  
