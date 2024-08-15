--1. List the names of the patients. The names should be in the form of "first_name last_name".
select Concat_WS(" ", first_name, last_name) as "Name" from patients;

--2. List the names and doses left of the vaccines. The results should be ordered by doses left in descending order.
select name, doses_left from vaccines order by doses_left desc;

--3. Find the patients who have received the Pfizer/BioNTech vaccine. For each patient, the result should include patient's id and name.
select id, Concat_WS(" ", first_name, last_name) as "Name" from patients where vaccine_id in (select id from vaccine where name = "Pfizer/BioNTech");

--4. List the names of the vaccines, and for each vaccine, the number of patients who have received that vaccine (or 0 if no patient has received that vaccine). The results should be ordered by the number of patients in descending order.
select V.name, count(V.id) as vaccine_count from patients P inner join vaccines V on P.vaccine_id = V.id group by V.name order by vaccine_count desc;

--5.
select P.id, Concat_WS(" ", P.first_name, P.last_name) as "Name", V.name, V.doses_required, P.first_dose_date from patients P inner join vaccines V on P.vaccine_id = V.id;