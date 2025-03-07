(* 
   Goran Delic
   89201217
   Bioinformatics 
*)


(* TASK 1 *)

let rec l3_of_3l_aux l1 l2 l3 acc =
  match l1, l2, l3 with
  | [], _, _
  | _, [], _
  | _, _, [] -> List.rev acc
  | x::xs, y::ys, z::zs ->
      let triple = (x, y, z) in
      l3_of_3l_aux xs ys zs (triple :: acc)
let l3_of_3l (l1, l2, l3) =
  l3_of_3l_aux l1 l2 l3 []

(*
  This function takes three lists as input and recursively builds a list of triples (a, b, c) 
  where a is an element from the first list, b is an element from the second list, 
  and c is an element from the third list. It stops when any of the input lists becomes empty, 
  ignoring any leftover elements in the other lists. Finally, it reverses the accumulated 
  list to maintain the order of the triples.
*)


(* TASK 2 *)

let map_fun_left functions elements =
  let rec apply_functions_all elements functions acc =
    match elements, functions with
    | [], _ | _, [] -> List.rev acc
    | x :: xs, f :: fs ->
        let result = List.fold_left (fun acc_fn fn -> fn acc_fn) x functions in
        apply_functions_all xs functions (result :: acc)
  in
  apply_functions_all elements functions []

(*
  The map_fun_left function operates by iterating over a list of functions and applying each function
  in sequence to corresponding elements in another list. It takes two arguments: a list of functions 
  and a list of elements. For each element in the input list, the function applies all functions from 
  left to right and accumulates the results. The resulting list contains the output of applying all 
  functions to each element, maintaining the order of elements from the input list. The function terminates 
  when either the list of functions or the list of elements becomes empty, disregarding any remaining 
  elements in the other list. Finally, the accumulated list is reversed to preserve the original order 
  of elements.
*)


(* TASK 3 *)

let explode_list strings =
  let explode_string s =
    let char_seq = String.to_seq s in
    List.of_seq char_seq
  in
  List.concat (List.map explode_string strings)

(*
  The explode_list function takes a list of strings as input. It maps the explode_string function over 
  each string to convert it into a list of characters. Then, it concatenates all these lists into a 
  single list of characters using List.concat.
*)

(* TASK 4 *)
 
let mode lst =
  let freq = Hashtbl.create (List.length lst) in
  List.iter (fun elem ->
      let count =
        try Hashtbl.find freq elem
        with Not_found -> 0
      in
      Hashtbl.replace freq elem (count + 1)
    ) lst;
  let max_count =
    Hashtbl.fold (fun _ count max_count ->
        if count > max_count then count else max_count
      ) freq 0
  in
  let mode_elem =
    Hashtbl.fold (fun elem count acc ->
        if count = max_count then (
          if List.mem elem acc then acc else elem :: acc
        ) else acc
      ) freq []
  in
  match mode_elem with
  | [elem] -> elem
  | [] -> failwith "No mode found (all elements have the same frequency)"
  | _ -> List.hd mode_elem

(*
 This function, mode, calculates the mode (most frequently occurring element) from a given list (lst).
 It uses a hashtable (freq) to count the occurrences of each element in the list. It then finds the maximum
 count among these occurrences. If there are ties for the maximum count, it returns the first element among 
 them. The function first populates the hashtable with element counts using List.iter and Hashtbl.replace. 
 Then, it finds the maximum count using Hashtbl.fold. After that, it collects all elements with counts equal 
 to the maximum count into a list (mode_elem) using another Hashtbl.fold. Finally, it returns the mode element,
 handling cases where there's a single mode element, multiple mode elements, or no mode element 
 (if all elements have the same frequency).
*)
