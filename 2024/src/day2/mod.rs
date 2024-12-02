pub fn day2() {
    let part1_result = day2_part1(INPUT);
    println!("Day 2, Part 1: {}", part1_result);
    let part2_result = day2_part2(INPUT);
    println!("Day 2, Part 2: {}", part2_result);
}

fn day2_part1(input: &str) -> i32 {
    parse_input(input)
        .iter()
        .map(|row| is_safe(row))
        .sum()
}

fn day2_part2(input: &str) -> i32 {
    parse_input(input)
        .iter()
        .map(|row| {
            if is_safe(row) == 1 {
                return 1;
            } else {
                for idx in 0..row.len() {
                    let ss = row
                        .iter()
                        .enumerate()
                        .filter(|(i, _)| *i != idx)
                        .map(|(_, &x)| x)
                        .collect();
                    if is_safe(&ss) == 1 {
                        return 1;
                    }
                }
                0
            }
        })
        .sum()
}

fn is_safe(row: &Vec<i32>) -> i32 {
    let is_ascending = row[0] < row[1];
    let mut prev = None;
    for cur in row.iter() {
        if prev.is_none() {
            prev = Some(cur);
            continue;
        }
        {
            let prev = prev.unwrap();
            let level_diff = (prev - cur).abs();
            let safe_direction = if is_ascending { prev < cur } else { prev > cur };
            if !(level_diff > 0 && level_diff < 4 && safe_direction) {
                return 0;
            }
        }
        prev = Some(cur);
    }
    1
}

fn parse_input(input: &str) -> Vec<Vec<i32>> {
    input
        .lines()
        .map(|line| {
            line.split_whitespace()
                .map(|x| x.parse::<i32>().unwrap())
                .collect::<Vec<i32>>()
        })
        .collect()
}

#[cfg(test)]
mod tests {
    use super::*;

    const TEST_INPUT: &str = include_str!("test_input.txt");

    #[test]
    fn test_part1() {
        let result = day2_part1(TEST_INPUT);
        assert_eq!(result, 2);
    }

    #[test]
    fn test_part2() {
        let result = day2_part2(TEST_INPUT);
        assert_eq!(result, 4);
    }
}

const INPUT: &str = include_str!("input.txt");
