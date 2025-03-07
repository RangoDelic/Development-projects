(* 
   Goran Delic
   89201217
   Bioinformatics 
*)


(*TASK 1*)
type pipe = 
  | LeftRight 
  | UpDown  
  | DownRight 
  | DownLeft
  | UpRight 
  | UpDownRight 
  | DownLeftRight

let chars = [|"\u{02550}"; "\u{02551}"; "\u{02554}"; "\u{02557}"; "\u{0255A}";
              "\u{02560}"; "\u{02566}"|]

let char_of_pipe = function
  | LeftRight -> chars.(0)
  | UpDown -> chars.(1)
  | DownRight -> chars.(2)
  | DownLeft -> chars.(3)
  | UpRight -> chars.(4)
  | UpDownRight -> chars.(5)
  | DownLeftRight -> chars.(6)

let print_matrix matrix =
  Array.iter (fun row ->
      Array.iter (fun pipe ->
          print_string (char_of_pipe pipe)
        ) row;
      print_newline ()
    ) matrix;;

let print_pipe_matrix_with_flow matrix = 
  Array.iter (fun row -> 
      Array.iter (fun pipe -> 
          match pipe with 
          | LeftRight -> print_string "═" 
          | _ -> print_string (char_of_pipe pipe) 
        ) row; 
      print_newline() 
    ) matrix ;;

let getleaks (matrix : pipe array array) : (int * int) list =
  let width = Array.length matrix in
  let height = Array.length matrix.(0) in
  let leaks = ref [] in
  let check_leak x y =
    if x < 0 || x >= width || y < 0 || y >= height then false
    else
      match matrix.(x).(y) with
      | LeftRight -> false
      | UpDown -> false
      | DownRight -> false
      | DownLeft -> false
      | UpRight -> false
      | UpDownRight -> false
      | DownLeftRight -> false
  in
  for i = 0 to width - 1 do
    for j = 0 to height - 1 do
      match matrix.(i).(j) with
      | pipe ->
          let flow = ref false in
          let next = match pipe with
            | LeftRight -> (i, j + 1)
            | UpDown -> (i + 1, j)
            | DownRight -> (i + 1, j + 1)
            | DownLeft -> (i + 1, j - 1)
            | UpRight -> (i - 1, j + 1)
            | UpDownRight -> (i + 1, j + 1)
            | DownLeftRight -> (i + 1, j - 1)
          in
          if (fst next) >= 0 && (fst next) < width && (snd next) >= 0 && (snd next) < height then begin
            match matrix.(fst next).(snd next) with
            | LeftRight -> ()
            | UpDown -> ()
            | DownRight -> ()
            | DownLeft -> ()
            | UpRight -> ()
            | UpDownRight -> ()
            | DownLeftRight -> flow := true
          end;
          if not !flow then begin
            matrix.(i).(j) <- LeftRight; (* Change the pipe to indicate flow *)
            if check_leak (fst next) (snd next) then leaks := (fst next, snd next) :: !leaks
          end
    done
  done; 
  !leaks
  

let p1=  [|[|DownLeft; UpDownRight; UpDownRight; UpDown; DownLeft; DownLeftRight;
             UpDownRight|];
           [|UpDownRight; LeftRight; DownLeftRight; LeftRight; DownLeftRight;
             DownLeft; DownLeft|];
           [|DownLeft; DownLeft; DownLeftRight; LeftRight; UpRight; DownLeftRight;
             LeftRight|];
           [|DownLeft; UpDown; UpDown; UpDown; UpDownRight; UpDown; UpDown|];
           [|LeftRight; DownRight; DownLeft; LeftRight; UpRight; DownLeft;
             DownRight|];
           [|DownRight; UpDown; UpDownRight; LeftRight; UpRight; UpRight; UpDown|]|];;

let p2=[|[|LeftRight; DownLeftRight; DownLeftRight; DownRight; UpRight; DownLeft;
           UpRight; DownRight; DownLeft; UpDownRight|];
         [|UpDownRight; UpDownRight; UpRight; DownLeft; UpDown; DownRight;
           UpDownRight; UpRight; UpDownRight; LeftRight|];
         [|LeftRight; UpDownRight; LeftRight; UpDown; DownLeftRight; DownLeft;
           UpDown; DownLeftRight; LeftRight; DownLeftRight|];
         [|UpDown; UpRight; DownLeftRight; DownLeft; DownLeft; UpDown;
           UpDownRight; LeftRight; UpDownRight; UpDown|];
         [|DownLeftRight; DownLeftRight; UpDownRight; DownLeft; UpDownRight;
           UpDownRight; DownRight; UpDown; DownLeft; UpDownRight|]|];;

print_matrix p1;
print_newline ();
let leaks_p1 = getleaks p1 in
print_pipe_matrix_with_flow p1;
print_newline ();
List.iter (fun (x, y) -> Printf.printf "(%d, %d) " x y) leaks_p1;
print_newline ();

print_matrix p2;
print_newline ();
let leaks_p2 = getleaks p2 in
print_pipe_matrix_with_flow p2;
print_newline ();
List.iter (fun (x, y) -> Printf.printf "(%d, %d) " x y) leaks_p2;
print_newline ();

(*TASK2*)

type 'a mtree = Emptytree | Node of 'a mnode
and 'a mnode = { mutable hd : 'a; mutable count : int; mutable ltl : 'a mtree; mutable rtl : 'a mtree }

let rec length (tree : 'a mtree) : int =
  match tree with
  | Emptytree -> 0
  | Node node -> node.count + length node.ltl + length node.rtl

let rec count (element : 'a) (tree : 'a mtree) : int =
  match tree with
  | Emptytree -> 0
  | Node node ->
      if node.hd = element then node.count
      else if element < node.hd then count element node.ltl
      else count element node.rtl

let rec insert (element : 'a) (tree : 'a mtree) : 'a mtree =
  match tree with
  | Emptytree ->
      let new_node = { hd = element; count = 1; ltl = Emptytree; rtl = Emptytree } in
      Node new_node
  | Node node ->
      if element = node.hd then begin
        node.count <- node.count + 1;
        tree
      end else if element < node.hd then begin
        node.ltl <- insert element node.ltl;
        tree
      end else begin
        node.rtl <- insert element node.rtl;
        tree
      end
